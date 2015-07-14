package com.sciencelk.controllers;

import com.sciencelk.common.ResponseHandler;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.services.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * * 
 * Created by SasithaG on 7/12/2015.
 */
@Controller
public class CategoryController {
    
    private static final String NOT_FOUND_MESSAGE = "Could not find requested resource";
    
    @Autowired
    private CategoryServiceInterface categoryService;
    
    @RequestMapping(value = "/v1/category", method = RequestMethod.GET)
    @ResponseBody
    public Object getCategoryList(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        try {
            return categoryService.getAllCategories();
        } catch (ItemNotFoundException e) {
            return ResponseHandler.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, NOT_FOUND_MESSAGE, response);
        }
    }
}
