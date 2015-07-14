package com.sciencelk.portal.controllers;

import com.sciencelk.dto.auth.AuthResponse;
import com.sciencelk.dto.common.ResponseType;
import com.sciencelk.portal.entities.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * *
 * Created by SasithaG on 3/27/2015.
 */
@Controller
public class IndexController {

    private static final String HAS_ERROR_LITERAL = "hasError";
    private static final String ANONYMOUS_USER = "anonymousUser";
    
    @Autowired
    private String licenseServerUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/app";
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String loadPortal(HttpServletRequest httpServletRequest, ModelMap model) {
        /*AuthResponse response = (AuthResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authHeader", response.getAuthToken());
        model.addAttribute("licenseServerUrl",licenseServerUrl );*/
        return "app/index";
    }

    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String loadManagementPortal(HttpServletRequest request, ModelMap model){
        AuthResponse response = (AuthResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authHeader", response.getAuthToken());
        model.addAttribute("userName", response.getUsername());
        return "management/index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        HttpServletResponse response, ModelMap model) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            if (error != null) {
                model.addAttribute(HAS_ERROR_LITERAL, true);
            } else {
                model.addAttribute(HAS_ERROR_LITERAL, false);
            }
            return "app/login";
        } else if (authentication.getPrincipal() instanceof AuthResponse) {
            AuthResponse authResponse = (AuthResponse) authentication.getPrincipal();
            if (authResponse.getStatus() == ResponseType.ERROR) {
                model.addAttribute(HAS_ERROR_LITERAL, true);
                model.addAttribute("errorMessage", authResponse.getAuthResult());
                return "app/login";
            }
        }
        return "redirect:/management";
    }

    @RequestMapping(value = "/checkSession", method = RequestMethod.GET)
    @ResponseBody
    public Object
    checkSession(HttpServletRequest request,
                 @RequestParam(required = false) String error,
                 @RequestParam(required = false) String logout,
                 HttpServletResponse response, ModelMap model) {
        // HTTP 1.1.
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // HTTP 1.0.
        response.setHeader("Pragma", "no-cache");
        // Proxies.
        response.setDateHeader("Expires", 0);
        SessionStatus sessionStatus = new SessionStatus();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!request.isRequestedSessionIdValid()) {
            // Session is invalid.
            sessionStatus.setSessionStatus(SessionStatus.EXPIRED);
        } else {
            if ("anonymousUser".equals(authentication.getPrincipal())) {
                sessionStatus.setSessionStatus(SessionStatus.NOSESSION);
            } else {
                sessionStatus.setSessionStatus(SessionStatus.ACTIVE);
            }
        }
        return sessionStatus;
    }
}
