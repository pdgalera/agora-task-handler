package pe.indigital.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.indigital.config.RestClientConfig;
import pe.indigital.config.TalonOneRestClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;

@Configuration
public class TalonOneRestClientConfig {

    private static final Logger logger = Logger.getLogger(TalonOneRestClientConfig.class.getName());

    @Value("${services-api.talon-one.base-url}")
    private String baseUrl;
    @Value("${services-api.talon-one.connect-timeout}")
    private long connectTimeout;
    @Value("${services-api.talon-one.read-timeout}")
    private long readTimeout;
    @Value("${services-api.talon-one.write-timeout}")
    private long writeTimeout;
    @Value("${services-api.talon-one.max-request}")
    private int maxRequest;

    @Bean
    public TalonOneRestClient talonOneRestClient() {
        logger.info(String.format("Create TalonOne Rest endpoint URL: %s",
                baseUrl));

        return new Retrofit.Builder()
                .client(new RestClientConfig()
                        .getHttpClient(maxRequest, connectTimeout,
                                readTimeout, writeTimeout))
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(TalonOneRestClient.class);
    }

}
