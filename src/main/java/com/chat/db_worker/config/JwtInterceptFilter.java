package com.chat.db_worker.config;

import com.chat.db_worker.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtInterceptFilter extends OncePerRequestFilter {



@Autowired
private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1. Missing or improperly formatted token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        String jwt = authHeader.substring(7);

        try {
            // 2. Validate your JWT here
            String username = jwtService.validateAndExtract(jwt);
            request.setAttribute("extractedUsername",username);
        filterChain.doFilter(request,response);
        } catch (Exception e) {
            // 4. Token is invalid or expired
            sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token validation failed: " + e.getMessage());
        }
    }

    private void sendJsonResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", "error");
        errorDetails.put("message", message);

        response.getWriter().write(errorDetails.toString());
    }
}