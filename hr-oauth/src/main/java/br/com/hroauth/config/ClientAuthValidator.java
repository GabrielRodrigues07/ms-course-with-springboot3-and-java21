package br.com.hroauth.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Component
@RefreshScope
public class ClientAuthValidator {

    @Value("${oauth.client.name}")
    private String clientName;

    @Value("${oauth.client.secret}")
    private String clientSecret;

    public boolean isValid(HttpServletRequest httpRequest) {

        String requestHeader = httpRequest.getHeader("Authorization");

        if (Objects.isNull(requestHeader) || !requestHeader.startsWith("Basic ")) return false;

        String base64CodeAuthorization = requestHeader.substring(6);

        String credentials = new String(Base64.getDecoder().decode(base64CodeAuthorization), StandardCharsets.UTF_8);

        String[] credentialsArray = credentials.split(":", 2);

        return Objects.equals(credentialsArray.length, 2)
                && Objects.equals(credentialsArray[0], clientName)
                && Objects.equals(credentialsArray[1], clientSecret);
    }
}
