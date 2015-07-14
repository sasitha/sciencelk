package com.sciencelk.controllers;

import com.sciencelk.common.interceptors.AuthenticationTokenHandler;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.UserDetails;
import com.sciencelk.dto.auth.AuthResponse;
import com.sciencelk.dto.auth.Credentials;
import com.sciencelk.dto.auth.Token;
import com.sciencelk.dto.auth.exceptions.AuthenticationException;
import com.sciencelk.dto.common.ResponseType;
import com.sciencelk.services.UserServiceInterface;
import org.apache.commons.codec.DecoderException;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * * 
 * Created by SasithaG on 6/16/2015.
 */
@Controller
public class UserController {
    
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserServiceInterface userService;
    
    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public Object authenticateUser(HttpSession session, @RequestBody Credentials credentials, HttpServletResponse response,
                                   HttpServletRequest request) throws IOException{
        AuthenticationTokenHandler tokenHandler = new AuthenticationTokenHandler();
        Token token = new Token();
        AuthResponse authResponse = new AuthResponse();

        try {
            if (userService.authenticateUser(credentials.getUsername(), credentials.getPassword())){
                UserDetails userDetails = userService.findByName(credentials.getUsername());

                Date date = new Date();
                long t = date.getTime();
                Date afterAddingTenMins = new Date(t + (10 * 60000));

                token.setExpiryTime(afterAddingTenMins);
                token.setUsername(credentials.getUsername());
                token.setId(userDetails.getUserId());

                try {
                    String authString = null;
                    return getAuthResponse(credentials, tokenHandler, token, authResponse, userDetails, authString);
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error(e.getMessage(), e);
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    authResponse.setStatus(ResponseType.ERROR);
                } catch (InvalidCipherTextException e) {
                    LOGGER.error(e.getMessage(), e);
                    authResponse.setStatus(ResponseType.ERROR);
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        } catch (AuthenticationException e) {
            LOGGER.error(e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            authResponse.setStatus(ResponseType.ERROR);
        } catch (ItemNotFoundException e) {
            LOGGER.error(e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            authResponse.setStatus(ResponseType.ERROR);
        }
        
        return authResponse;
    }
    
    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllUsers(HttpServletResponse response){
        return null;
    }
    
    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserDetails(HttpServletResponse response,
                                 @PathVariable Integer id){
        return null;
    }
    
    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    @ResponseBody
    public Object addNewUser(HttpServletResponse response, 
                             @RequestBody UserDetails userDetails){
        return null;
    }
    
    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object updateUserDetails(HttpServletResponse response,
                                    @PathVariable Integer id,
                                    @RequestBody UserDetails userDetails){
        return null;        
    }


    private AuthResponse getAuthResponse(Credentials authentication, AuthenticationTokenHandler tokenHandler, Token token, AuthResponse authResponse, UserDetails userDetails, String authString) throws UnsupportedEncodingException, InvalidCipherTextException, ItemNotFoundException {

        String authenticationString = authString;
        try {
            authenticationString = tokenHandler.createAuthString(token);
        } catch (DecoderException e) {
            LOGGER.error(e.getMessage(), e);
        }

        authResponse.setAuthToken(authenticationString);
        authResponse.setStatus(ResponseType.SUCCESS);
        authResponse.setUsername(authentication.getUsername());
        return authResponse;
    }
    
}
