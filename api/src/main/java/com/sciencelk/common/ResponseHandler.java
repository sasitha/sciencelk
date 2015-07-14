package com.sciencelk.common;

import com.sciencelk.dto.common.Response;
import com.sciencelk.dto.common.ResponseType;

import javax.servlet.http.HttpServletResponse;

/**
 * *
 * Created by SasithaG on 6/16/2015.
 */
public class ResponseHandler {
    
    private ResponseHandler(){

    }
    
    public static Response sendErrorResponse(int errorCode, String errorMessage, HttpServletResponse servletResponse){
        Response response = new Response();
        servletResponse.setStatus(errorCode);
        response.setMessage(errorMessage);
        response.setResponseType(ResponseType.ERROR);
        return response;
    }
    
    public static Response sendSuccessResponse(String message, HttpServletResponse servletResponse){
        Response response =  new Response();
        servletResponse.setStatus(HttpServletResponse.SC_OK);
        response.setMessage(message);
        response.setResponseType(ResponseType.SUCCESS);
        return response;
    }
}
