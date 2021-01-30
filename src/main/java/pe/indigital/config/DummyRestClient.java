package pe.indigital.config;

import pe.indigital.proxy.request.DummyRequest;
import pe.indigital.proxy.response.DummyResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface DummyRestClient {

    @PUT("/v1/dummy/{dummyId}")
    Call<DummyResponse> createSession(
            @Path("dummyId") String dummyId,
            @Body DummyRequest dummyRequest
    );
}
