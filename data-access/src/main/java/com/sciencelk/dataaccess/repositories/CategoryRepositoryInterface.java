package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.DAOInterface;
import com.sciencelk.dataaccess.entities.Category;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * *
 * Created by SasithaG on 7/12/2015.
 */
public interface CategoryRepositoryInterface extends DAOInterface<Category, Integer>{
    
    List<Category> getSortedCategoryList() throws ItemNotFoundException;
}
