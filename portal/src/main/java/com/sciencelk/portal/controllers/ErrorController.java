package com.sciencelk.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() {
        return "app/404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "app/403";
    }
}
