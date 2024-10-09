package com.university.librarymanagementsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (only for development purposes)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/api/users/**", // Public API endpoints
                                "/login", // Allow access to login page
                                "/register", // Allow access to registration page
                                "/home", // Allow access to home page
                                "/static/**") // Allow access to static resources (e.g., CSS, JS)
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login // Enable form-based authentication
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard after login
                        .permitAll())
                .logout(logout -> logout // Enable logout functionality
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll())
                .sessionManagement(management -> management
                        .sessionFixation().migrateSession() // Prevent session fixation attacks
                        .maximumSessions(1) // Allow only one session per user
                        .and()
                        .invalidSessionUrl("/login?invalidSession")); // Redirect to login if session is invalid
        return http.build();
    }

    private Customizer<CorsConfigurer<HttpSecurity>> withDefaults() {
        return corsConfigurer -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.addAllowedOriginPattern("*"); // Allow all origins (adjust as needed)
            configuration.addAllowedMethod("*"); // Allow all HTTP methods
            configuration.addAllowedHeader("*"); // Allow all headers

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            corsConfigurer.configurationSource(source);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 12, 3);
    }
}
