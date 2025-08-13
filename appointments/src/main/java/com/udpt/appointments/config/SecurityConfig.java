package com.udpt.appointments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    requests.anyRequest().permitAll();
                })
                .build();

    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomSecurityFilter filter, CorsConfigurationSource corsConfigurationSource) throws Exception {
//        return http.csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(requests -> {
//                    // help define permission for access links
//                    requests.requestMatchers("/api/users/add", "/api/users/update-role", "/api/users/delete/{id}").hasRole("ADMIN");
//                    requests.requestMatchers("/api/login", "/api/register", "/api/login/refresh").permitAll();
//                    requests.requestMatchers("/api/spaces/images/{spaceName}").permitAll();
//                    requests.requestMatchers("/api/spaces/add", "/api/locations/add", "/api/locations/add-space-image/{locationName}/{spaceName}", "/api/locations/delete-space-image/{locationName}/{spaceName}/{fileName}").hasRole("ADMIN");
//                    requests.requestMatchers("/api/spaces/add-image").hasRole("ADMIN");
//                    requests.requestMatchers(HttpMethod.GET, "/api/spaces").permitAll();
//                    requests.requestMatchers(HttpMethod.POST, "/api/spaces/add-combo/{name}", "/api/spaces/add-location/{spaceName}", "/api/spaces/remove-combo/").hasRole("ADMIN");
//                    requests.requestMatchers(HttpMethod.GET, "/api/bookings").permitAll();
//
//                    requests.requestMatchers("/download/**").permitAll();
//
//                    requests.anyRequest().authenticated();
//                })
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//
//    }

}
