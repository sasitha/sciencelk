package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.AbstractDAO;
import com.sciencelk.dataaccess.entities.User;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
@Transactional
@Repository
public class UserRepository extends AbstractDAO<User, Integer> implements UserRepositoryInterface{
    
    public UserRepository(){
        super(User.class);
    }

    @Override
    public User findByUserName(String userName) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name",userName));
        criteria.add(Restrictions.ne("locked", true));
        criteria.setMaxResults(1);
        criteria.setFirstResult(0);
        return (User)criteria.uniqueResult();
    }

    @Override
    public boolean authenticate(String userName, String password) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name",userName));
        criteria.setMaxResults(1);
        criteria.setFirstResult(0);
        User currentUser = (User)criteria.uniqueResult();
        if(currentUser != null){
            if (currentUser.isLocked()){
                throw new ItemNotFoundException("Your login is locked. Please contact system administrator");
            }else {
                boolean authResult = password.equals(currentUser.getPassword());
                if (authResult){
                    currentUser.setLastlogin(new Date());
                    saveOrUpdate(currentUser);
                }
                return authResult;
            }
        }else {
            throw new ItemNotFoundException("User name does not exists");
        }
    }

    private boolean getBooleanValue(byte byteData) {
        return byteData != 0;
    }
}
