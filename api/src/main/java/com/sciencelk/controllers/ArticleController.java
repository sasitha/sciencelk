package com.sciencelk.controllers;

import com.sciencelk.common.ResponseHandler;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.ArticleDetails;
import com.sciencelk.services.ArticleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * * 
 * Created by SasithaG on 6/16/2015.
 */


@Controller
public class ArticleController {
    
    

    @Autowired
    private ArticleServiceInterface articleService;
    
    @RequestMapping(value = "/v1/article", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllArticles(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        try {
            return articleService.getAllArticles();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return ResponseHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), response);
        }
    }
    
    @RequestMapping(value = "/v1/category/{id}/article", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticleByCategory(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable Integer id,
                                       @RequestParam(value = "page", defaultValue = "1") String page,
                                       @RequestParam(value = "pageSize", defaultValue = "10") String pageSize){

        try {
            return articleService.getArticleByCategory(id, Integer.valueOf(page), Integer.valueOf(pageSize));
        } catch (ItemNotFoundException e) {
            return ResponseHandler.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage(), response);
        }
    }
    
    @RequestMapping(value = "/v1/article", method = RequestMethod.POST)
    @ResponseBody
    public Object addNewArticle(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                @RequestBody ArticleDetails articleDetails){
        return articleService.addNewArticle(articleDetails);
    }
    
    @RequestMapping(value = "/v1/article/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticle(HttpServletResponse response,
                             @PathVariable Integer id){
        try {
            return articleService.getArticleById(id);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return ResponseHandler.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, "Not found", response);
        }
    }
    
    @RequestMapping(value = "/v1/article/tag/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticlesByTagId(HttpServletResponse response,
                                     @PathVariable Integer id){
        return null;        
    }

}
