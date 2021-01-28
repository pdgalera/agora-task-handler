package pe.indigital.proxy;


import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.indigital.TaskHandlerController;
import pe.indigital.config.TalonConfig;
import pe.indigital.config.TalonOneRestClient;
import pe.indigital.proxy.request.CloseSession;
import pe.indigital.proxy.request.CreateEventAttributesRequest;
import pe.indigital.proxy.request.CreateSessionRequest;
import pe.indigital.proxy.response.TalonOneResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class TalonOneRestClientAdapter {

    private static final Logger logger = Logger.getLogger(TalonOneRestClientAdapter.class.getName());

    private static final CloseSession CLOSE_SESSION = new CloseSession();

    private final static String OPEN_SESSION = "open";

    private final static String TALON_ONE_TYPE = "DELAY_FIRST_PURCHASE";

    private final TalonOneRestClient talonOneRestClient;

    private final TalonConfig talonConfig;

    @Autowired
    public TalonOneRestClientAdapter(TalonOneRestClient talonOneRestClient,
                                     TalonConfig talonConfig) {
        this.talonOneRestClient = talonOneRestClient;
        this.talonConfig = talonConfig;
    }

    public void callToTalonOne(String userId, BigDecimal amount) {
        String sessionId = UUID.randomUUID().toString();
        String authHeader = talonConfig.getAuthenticationHeader();
        CreateSessionRequest request = new CreateSessionRequest();
        request.setProfileId(userId);
        request.setState(OPEN_SESSION);
        CreateEventAttributesRequest attributesRequest = new CreateEventAttributesRequest();
        attributesRequest.setType(TALON_ONE_TYPE);
        attributesRequest.setAmount(amount);
        request.setAttributes(attributesRequest);
        this.perform(talonOneRestClient.createSession(sessionId, request, authHeader));
        this.perform(talonOneRestClient.closeSession(sessionId, CLOSE_SESSION, authHeader));
    }

    private void perform(Call<TalonOneResponse> talonOneResponseCall) {
        try {
            Response<TalonOneResponse> talonOneResponseResponse = talonOneResponseCall.execute();
            logger.info("response from talon one " + talonOneResponseResponse);
            if (!talonOneResponseResponse.isSuccessful()) {
                logger.info("Error executing call to TalonOne");
                ResponseBody responseBody = talonOneResponseResponse.errorBody();
                String errorBody = responseBody.string();
                String errorCode = String.valueOf(talonOneResponseResponse.code());
                throw new RuntimeException("Error code " + errorCode + ", error body = " + errorBody);
            }
        } catch (IOException e) {
            logger.info("Serialization error, " + e);
            throw new RuntimeException("Serialization error");
        }
    }

}
