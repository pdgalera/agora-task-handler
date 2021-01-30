package pe.indigital.proxy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.indigital.config.DummyRestClient;
import pe.indigital.proxy.request.DummyRequest;
import pe.indigital.proxy.response.DummyResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class DummyRestClientAdapter {

    private static final Logger logger = Logger.getLogger(DummyRestClientAdapter.class.getName());

    private final DummyRestClient dummyRestClient;

    @Autowired
    public DummyRestClientAdapter(DummyRestClient dummyRestClient) {
        this.dummyRestClient = dummyRestClient;
    }

    public void callDummy() {
        DummyRequest dummyRequest = new DummyRequest();
        try {
            Response<DummyResponse> response = dummyRestClient.createSession("1", dummyRequest).execute();
            if (!response.isSuccessful()) {
                logger.info("ERROR calling Dummy endpoint");
            } else {
                DummyResponse dummyResponse = response.body();
                logger.info("INFO reponse " + dummyResponse.dummyName);
                logger.info("INFO reponse " + dummyResponse.dummyDescription);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
