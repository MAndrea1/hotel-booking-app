package com.example.hotelbookingapp.config;

import com.example.hotelbookingapp.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnePerRequest extends OncePerRequestFilter{

    @Autowired
    private TokenGen tokenGen;

    @Autowired
    UserDetailsServiceImp userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            if (token != null && tokenGen.validateToken(token)) {
                String id = tokenGen.getIdFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(id);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("--Error in TokenGen - doFilterInternal--");
        }

        filterChain.doFilter(request,response);
    }

    private String getToken (HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer", "");
        return null;
    }
}
