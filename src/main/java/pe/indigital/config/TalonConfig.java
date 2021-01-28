package pe.indigital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TalonConfig {

    private final static String AUTH_HEADER = "ApiKey-v1 %s";
    @Value("${services-api.talon-one.key}")
    private String key;

    public String getAuthenticationHeader() {
        return String.format(AUTH_HEADER, key);
    }

}
