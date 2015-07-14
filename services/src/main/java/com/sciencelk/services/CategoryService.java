package com.sciencelk.services;

import com.sciencelk.dataaccess.entities.Category;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dataaccess.repositories.CategoryRepositoryInterface;
import com.sciencelk.dto.CategoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * * 
 * Created by SasithaG on 7/12/2015.
 */
@Service
public class CategoryService implements CategoryServiceInterface{

    
    @Autowired
    private CategoryRepositoryInterface categoryRepository;
    
    @Transactional
    @Override
    public List<CategoryDetails> getAllCategories() throws ItemNotFoundException {
        List<CategoryDetails> categoryDetailsList = new ArrayList<CategoryDetails>();
        for (Category category : categoryRepository.getSortedCategoryList()){
            categoryDetailsList.add(getCategoryDetails(category));
        }
        return categoryDetailsList;
    }

    @Override
    public CategoryDetails getCategoryById(int id) throws ItemNotFoundException {
        return getCategoryDetails(categoryRepository.findById(id));
    }
    
    private CategoryDetails getCategoryDetails(Category category){
        CategoryDetails categoryDetails = new CategoryDetails();
        categoryDetails.setId(category.getId());
        categoryDetails.setName(category.getName());
        return categoryDetails;
    }
}
