package com.vrx.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // this class provides configurations
@EnableWebSecurity //enable web security features in the app
@EnableMethodSecurity //enables @PreAuthorize() - authorization for method level
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()); //any request is authenticated
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());// configures http basic authentication with default settings
        return http.build();
    }


    @Bean
    public UserDetailsService getUserDetails(){
        UserDetails user = User.withUsername("user").password("{noop}user123").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}admin123").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }
}
