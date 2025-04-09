package org.vikas.todoapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vikas.todoapp.service.JwtService;
import org.vikas.todoapp.service.MyUserDetailsService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

//    @Autowired
//    ApplicationContext context;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken upAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
           upAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
           SecurityContextHolder.getContext().setAuthentication(upAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
