package br.com.hroauth.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Component
public class ClientAuthValidator {

    private final String clientName = "myappname123";
    private final String clientSecret = "myappsecret123";

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
