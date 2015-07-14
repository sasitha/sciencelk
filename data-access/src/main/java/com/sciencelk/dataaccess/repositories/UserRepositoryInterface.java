package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.DAOInterface;
import com.sciencelk.dataaccess.entities.User;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
public interface UserRepositoryInterface extends DAOInterface<User, Integer>{
    
    User findByUserName(String userName) throws ItemNotFoundException;
    
    boolean authenticate(String userName, String password) throws ItemNotFoundException;
}
