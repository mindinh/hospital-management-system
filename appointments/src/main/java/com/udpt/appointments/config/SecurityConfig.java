package com.udpt.appointments.config;

import com.udpt.appointments.filter.CustomSecurityFilter;
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

                    // authorize services endpoints
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/services/add").hasRole("ADMIN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/services/*").hasAnyRole("ADMIN", "BACSI");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/services/all-service").hasAnyRole("ADMIN", "BACSI");


                    // authorize appointments command endpoints
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/appointments/create").hasAnyRole("ADMIN", "TIEPTAN", "BENHNHAN");
                    requests.requestMatchers(HttpMethod.POST, "/api/v1/appointments/book").hasAnyRole("BENHNHAN");
                    requests.requestMatchers(HttpMethod.PUT, "/api/v1/appointments/checkin/*").hasAnyRole("ADMIN", "TIEPTAN");
                    requests.requestMatchers(HttpMethod.DELETE, "/api/v1/appointments/cancel/*").hasAnyRole("ADMIN", "TIEPTAN", "BENHNHAN");

                    // authorize appointments query endpoints
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/appointments/my-appointments").hasAnyRole("BENHNHAN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/appointments/search").hasAnyRole("ADMIN", "TIEPTAN");
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/appointments/statistic").hasAnyRole("ADMIN");




                    requests.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
