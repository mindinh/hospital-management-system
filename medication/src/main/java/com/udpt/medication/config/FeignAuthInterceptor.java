package com.udpt.medication.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    public FeignAuthInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            template.header("Authorization", authHeader);
        }
    }
}

