package com.sciencelk.portal.common;

import com.google.gson.Gson;
import com.sciencelk.dto.auth.AuthResponse;
import com.sciencelk.dto.auth.Credentials;
import com.sciencelk.dto.common.ResponseType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * Created by SasithaG on 3/27/2015.
 */
public class AuthenticationHandler implements AuthenticationProvider {

    private static final Logger LOG = Logger.getLogger(AuthenticationHandler.class);

    private static final Logger LOGGER = Logger.getLogger(AuthenticationHandler.class);


    @Autowired
    String authUrl;

    public AuthenticationHandler() {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extract the username and password
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Perform REST Service authentication
        String authToken = serviceAuthentication(username, password);
        if (authToken == null) {
            throw new AuthenticationServiceException("Authentication failed");
        }

        Gson gson = new Gson();
        AuthResponse authResponse = gson.fromJson(authToken, AuthResponse.class);

        try {
            if (authResponse.getStatus().equals(ResponseType.SUCCESS)) {
                List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new UsernamePasswordAuthenticationToken(authResponse, username, grantedAuths);
            } else {
                return new UsernamePasswordAuthenticationToken(authResponse, "", null);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new AuthenticationServiceException("Authentication failed : Server Error");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private String serviceAuthentication(String username, String password) throws AuthenticationException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate authService = new RestTemplate();

        // Configuration of the Rest template
        authService.getMessageConverters().clear();
        authService.getMessageConverters().add(new StringHttpMessageConverter());
        authService.getMessageConverters().add(new FormHttpMessageConverter());
        authService.getMessageConverters().add(new ByteArrayHttpMessageConverter());

        Gson gson = new Gson();

        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        try {
            HttpEntity request = new HttpEntity(gson.toJson(credentials), headers);
            ResponseEntity<String> result = authService.exchange(authUrl, HttpMethod.POST, request, String.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                return result.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOGGER.error(e);
            throw new AuthenticationServiceException("Internal Server Error");
        }
        return null;
    }

}
