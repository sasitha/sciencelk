package com.sciencelk.controllers;

import com.sciencelk.dto.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * * 
 * Created by SasithaG on 6/16/2015.
 */
@Controller
public class TagController {
    
    @RequestMapping(value = "/v1/tag", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllTags(HttpServletResponse response){
        return null;        
    }
    
    @RequestMapping(value = "/v1/tag", method = RequestMethod.POST)
    @ResponseBody
    public Object addNewTag(HttpServletResponse response,
                            @RequestBody Tag tag){
        return null;        
    }
}
