package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.AbstractDAO;
import com.sciencelk.dataaccess.entities.Privilege;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
@Transactional
@Repository
public class PrivilegeRepository extends AbstractDAO<Privilege, Integer> implements PrivilegeRepositoryInterface {
    
    public PrivilegeRepository(){
        super(Privilege.class);
    }
}
