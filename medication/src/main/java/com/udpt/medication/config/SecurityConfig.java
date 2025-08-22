package com.udpt.medication.config;

import com.udpt.medication.filter.CustomSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity // allow configuring security
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Allow specific origin
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomSecurityFilter filter, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    // authorize medications endpoints
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/medications/create").hasRole("ADMIN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/medications/details").permitAll();
                    requests.requestMatchers(HttpMethod.PUT,"/api/v1/accounts/update/{id}").hasAnyRole("ADMIN", "DUOCSI");
                    requests.requestMatchers(HttpMethod.DELETE,"/api/v1/medications/delete").hasRole("ADMIN");

                    // authorize prescriptions endpoints
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/prescriptions/create").hasAnyRole("BACSI", "ADMIN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/prescriptions").permitAll();
                    requests.requestMatchers(HttpMethod.PUT, "/api/v1/prescriptions/ready/{maDonThuoc}").hasAnyRole("DUOCSI", "ADMIN");
                    requests.requestMatchers(HttpMethod.PUT, "/api/v1/prescriptions/checkout/{maDonThuoc}").hasAnyRole("DUOCSI", "ADMIN");


                    requests.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
