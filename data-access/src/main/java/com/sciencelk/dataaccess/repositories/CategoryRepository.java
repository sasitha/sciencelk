package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.AbstractDAO;
import com.sciencelk.dataaccess.entities.Category;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * * 
 * Created by SasithaG on 7/12/2015.
 */
@Repository
@Transactional
public class CategoryRepository extends AbstractDAO<Category, Integer> implements CategoryRepositoryInterface{
    
    public CategoryRepository(){super(Category.class);}

    @Transactional
    @Override
    public List<Category> getSortedCategoryList() throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
