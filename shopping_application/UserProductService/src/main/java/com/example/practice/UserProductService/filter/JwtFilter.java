package com.example.practice.UserProductService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)  servletResponse;
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter printWriter = response.getWriter();
            printWriter.println("Missing or invalid token");
        }else {
            String jwtToken = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("mykey").parseClaimsJws(jwtToken).getBody();
            String username = Jwts.parser().setSigningKey("mykey").parseClaimsJws(jwtToken).getBody().getSubject();
            request.setAttribute("username", username);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
