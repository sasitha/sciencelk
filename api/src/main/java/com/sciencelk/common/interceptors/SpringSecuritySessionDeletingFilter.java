package com.sciencelk.common.interceptors;

import com.google.gson.Gson;
import com.sciencelk.common.ResponseHandler;
import com.sciencelk.common.interceptors.exeptions.SecurityTokenException;
import com.sciencelk.dto.auth.Token;
import com.sciencelk.dto.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SpringSecuritySessionDeletingFilter extends GenericFilterBean implements Filter {

    public static final String FORBIDDEN = "Forbidden";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DB_CONNECTIVITY = "Could not establish a connection with the database";


    @Autowired
    String authenticationURL;

    @Autowired
    String fileUploadUrl;

    @Autowired
    String metaFileUrl;

    /**
     * Filter to initialize the SecurityContextHolder.
     *
     * @param servletRequest  The HttpRequest object.
     * @param servletResponse The HttpResponse object.
     * @param filterChain    The entire filter chain.
     * @throws java.io.IOException            Any IOExceptions.
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();

        if (!request.getRequestURL().toString().contains(authenticationURL) && !request.getRequestURL().toString().contains(metaFileUrl)) {
            CheckAndRemoveContextForStateless(session);

            SecurityContext c = SecurityContextHolder.createEmptyContext();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            AuthenticationTokenHandler authenticationHandler = new AuthenticationTokenHandler();
            String authToken = request.getHeader("AuthToken");

            if (authToken != null) {
                if (createContext(response, c, authorities, authenticationHandler, authToken)) {
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void CheckAndRemoveContextForStateless(HttpSession session) {
        if (session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {
            session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        }
    }

    private boolean createContext(HttpServletResponse response, SecurityContext c, List<GrantedAuthority> authorities, AuthenticationTokenHandler authenticationHandler, String authToken) throws IOException {
        try {
            Token token = authenticationHandler.extractToken(authToken);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(token, token, authorities);
            c.setAuthentication(authenticationToken);
            SecurityContextHolder.clearContext();
            SecurityContextHolder.setContext(c);
        } catch (SecurityTokenException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, FORBIDDEN);
            return true;
        } catch (TransactionSystemException e) {
            logger.error(e.getMessage(), e);
            sendJsonError(response, DB_CONNECTIVITY);
            return true;
        }

        return false;
    }


    private void sendJsonError(HttpServletResponse response, String message) throws IOException {
        Response responseObj = ResponseHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST, message, response);
        Gson gson = new Gson();
        String error = gson.toJson(responseObj, Response.class);
        response.setContentType(APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
        writer.write(error);
        writer.flush();
        writer.close();
    }
}
