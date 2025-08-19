package com.udpt.accounts.filter;


import com.udpt.accounts.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenHeader = request.getHeader("Authorization");
        if (authenHeader != null && authenHeader.startsWith("Bearer ")) {
            String token = authenHeader.substring(7);

            boolean isSuccess = jwtHelper.decrypt(token);

            System.out.println("custom security filter is success: " + isSuccess);

            if (isSuccess) {
                String role = jwtHelper.getDataToken(token);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                // tạo ra chứng thực để bypass filter của security
                SecurityContext securityContext = SecurityContextHolder.getContext();

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken("", "", authorities);
                securityContext.setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
