package pe.indigital.config;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class RestClientConfig {

    public OkHttpClient getHttpClient(int maxRequest, long connectTimeout,
                                      long readTimeout, long writeTimeout) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(maxRequest);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .dispatcher(dispatcher).connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((s, sslSession) -> true).build();

        return httpClient;

    }

}
