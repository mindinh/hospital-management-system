package com.udpt.patients.config;

import com.udpt.patients.filter.CustomSecurityFilter;
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
                    // authorize patients endpoints
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/patients/create").permitAll();
                    requests.requestMatchers(HttpMethod.POST,"api/v1/patients/add-record").hasAnyRole("ADMIN", "BACSI");
                    requests.requestMatchers(HttpMethod.PUT, "/api/v1/patients/update").hasAnyRole("ADMIN", "BENHNHAN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/patients/details/*").hasAnyRole("ADMIN", "BACSI");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/patients/details/me").hasAnyRole("BENHNHAN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/patients/search").hasAnyRole("ADMIN", "BACSI");
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/patients/add-record").hasAnyRole("ADMIN", "BACSI");


                    // authorize records endpoints
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/records/all-records").hasAnyRole("ADMIN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/records/details/{id}").hasAnyRole("ADMIN", "BACSI");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/records/details/me").hasAnyRole("BENHNHAN");


                    requests.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
