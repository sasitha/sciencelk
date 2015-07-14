package com.sciencelk.services;

import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.CategoryDetails;

import java.util.List;

/**
 * * 
 * Created by SasithaG on 7/12/2015.
 */
public interface CategoryServiceInterface {
    
    List<CategoryDetails> getAllCategories() throws ItemNotFoundException;
    
    CategoryDetails getCategoryById(int id) throws ItemNotFoundException;
}
