package pe.indigital.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.indigital.config.RestClientConfig;
import pe.indigital.config.DummyRestClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;

@Configuration
public class DummyRestClientConfig {

    private static final Logger logger = Logger.getLogger(DummyRestClientConfig.class.getName());

    @Value("${services-api.dummy.base-url}")
    private String baseUrl;
    @Value("${services-api.dummy.connect-timeout}")
    private long connectTimeout;
    @Value("${services-api.dummy.read-timeout}")
    private long readTimeout;
    @Value("${services-api.dummy.write-timeout}")
    private long writeTimeout;
    @Value("${services-api.dummy.max-request}")
    private int maxRequest;

    @Bean
    public DummyRestClient dummyRestClient() {
        logger.info(String.format("Create Dummy Rest endpoint URL: %s",
                baseUrl));

        return new Retrofit.Builder()
                .client(new RestClientConfig()
                        .getHttpClient(maxRequest, connectTimeout,
                                readTimeout, writeTimeout))
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(DummyRestClient.class);
    }

}
