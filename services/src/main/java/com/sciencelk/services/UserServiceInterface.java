package com.sciencelk.services;

import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.UserDetails;
import com.sciencelk.dto.auth.exceptions.AuthenticationException;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
public interface UserServiceInterface {

    boolean authenticateUser(String userName, String password) throws AuthenticationException;

    UserDetails findByName(String userName) throws ItemNotFoundException;
}
