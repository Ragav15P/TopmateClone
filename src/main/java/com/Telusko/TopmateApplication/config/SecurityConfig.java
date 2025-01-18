package com.Telusko.TopmateApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable for simplicity; replace with token-based CSRF handling for production.
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless sessions (e.g., JWT).
//            .and()
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/auth/**").permitAll() // Public access for auth endpoints.
//                .requestMatchers("/api/users/register").permitAll();
//                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Restrict to admins.
//                .anyRequest().authenticated() // Secure all other endpoints.
//            )
//            .formLogin().disable() // Disable default login page.
//            .httpBasic().disable(); // Disable basic auth for better security (use JWT or OAuth2 instead).
//        
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity during development
            .authorizeHttpRequests(auth -> auth
            		 .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/users/register").permitAll() // Allow public access to registration
                .anyRequest().authenticated() // Protect all other endpoints
            )
            .formLogin().disable(); // Disable default login page for API
        return http.build();
    }

}
