// package com.spring.spring_security.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import com.spring.spring_security.service.CustomUserDetailsService;

// /**
//  * Configuration class for Spring Security.
//  */
// @EnableWebSecurity
// @Configuration
// public class SecurityConfig {

//     private CustomUserDetailsService userDetailsService;

//     /**
//      * Initializes the security configuration.
//      *
//      * @param userDetailsService custom user details service
//      */
//     public SecurityConfig(CustomUserDetailsService userDetailsService) {
//         this.userDetailsService = userDetailsService;
//     }

//     /**
//      * Configures the application's security filter chain.
//      *
//      * @param security HTTP security configuration
//      * @return configured security filter chain
//      * @throws Exception if the security configuration fails
//      */
//     @Bean
//     SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//         return security
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers("/api/auth/**").permitAll()
//                         .anyRequest().authenticated())
//                 .httpBasic(Customizer.withDefaults())
//                 .build();
//     }

//     /**
//      * Creates a BCrypt password encoder for securely hashing passwords.
//      *
//      * @return BCrypt password encoder
//      */
//     @Bean
//     BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     /**
//      * Exposes the authentication manager as a Spring bean.
//      *
//      * @param authentication Spring Security authentication configuration
//      * @return configured authentication manager
//      */
//     @Bean
//     AuthenticationManager authenticationManager(
//             AuthenticationConfiguration authentication) {
//         return authentication.getAuthenticationManager();
//     }

//     public CustomUserDetailsService getUserDetailsService() {
//         return userDetailsService;
//     }

//     public void setUserDetailsService(
//             CustomUserDetailsService userDetailsService) {
//         this.userDetailsService = userDetailsService;
//     }
// }