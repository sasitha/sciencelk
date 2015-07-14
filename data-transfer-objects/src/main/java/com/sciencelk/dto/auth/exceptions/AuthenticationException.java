package com.sciencelk.dto.auth.exceptions;

/**
 * * 
 * Created by sasithag on 7/6/2015.
 */
public class AuthenticationException extends Throwable {
    
    public AuthenticationException(String message){super(message);}
    
    public AuthenticationException(Throwable cause){super(cause);}
    
    public AuthenticationException(String message, Throwable cause){super(message, cause);}
}
