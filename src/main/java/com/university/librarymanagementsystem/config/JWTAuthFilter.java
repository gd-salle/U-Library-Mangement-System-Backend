package com.university.librarymanagementsystem.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.university.librarymanagementsystem.service.JWTUtils;
import com.university.librarymanagementsystem.service.StakeHolderService;
import com.university.librarymanagementsystem.service.impl.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private MyUserDetailsService ourUserDetailsService;


    @Override
    //
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // List of paths to skip JWT authentication
        String requestPath = request.getServletPath();
        if (requestPath.startsWith("/verify/**")
                || requestPath.startsWith("/public/")) {
            // Skip JWT authentication for these paths
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // Check if Authorization header is missing or doesn't contain a token
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7); // Extract token
        userEmail = jwtUtils.extractUsername(jwtToken); // Extract user email from token

        // If a user email was extracted and there's no authentication in the context,
        // proceed
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = ourUserDetailsService.loadUserByUsername(userEmail);

            // Check if token is valid
            boolean isValidToken = jwtUtils.isTokenValid(jwtToken, userDetails);
            System.out.println("Is Token Valid: " + isValidToken); // Debugging token validity

            if (isValidToken) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); // Set the authentication in the
                                                                                 // context
            }
        }
        filterChain.doFilter(request, response);
    }
}
