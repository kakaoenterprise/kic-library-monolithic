package com.kep.library.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@Component
public class ManagerAccountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String jwt_token = httpRequest.getHeader("jwt_token");

        if(null == jwt_token && null != httpRequest.getCookies() && Arrays.stream(httpRequest.getCookies()).anyMatch(cookie -> cookie.getName().equals("jwt_token")) ) {
            Optional<Cookie> cookie = Arrays.stream(httpRequest.getCookies()).filter(c -> c.getName().equals("jwt_token")).findFirst();
            jwt_token = cookie.get().getValue();
        }
        if( null == httpRequest.getSession().getAttribute("authenticated"))
            httpRequest.getSession().setAttribute("authenticated", "false");

        if( null != jwt_token && jwt_token.length() > 0){
            httpRequest.getSession().setAttribute("authenticated", "true");
        }else
            httpRequest.getSession().setAttribute("authenticated", "false");

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
