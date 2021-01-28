package pe.indigital.config;

import pe.indigital.proxy.request.CloseSession;
import pe.indigital.proxy.request.CreateSessionRequest;
import pe.indigital.proxy.response.TalonOneResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface TalonOneRestClient {

    @Headers("Content-Type: application/json")
    @PUT("/v1/customer_sessions/{sessionId}")
    Call<TalonOneResponse> createSession(
            @Path("sessionId") String sessionId,
            @Body CreateSessionRequest createSession,
            @Header("Authorization") String authorization
    );

    @Headers("Content-Type: application/json")
    @PUT("/v1/customer_sessions/{sessionId}")
    Call<TalonOneResponse> closeSession(
            @Path("sessionId") String sessionId,
            @Body CloseSession closeSession,
            @Header("Authorization") String authorization);

}
