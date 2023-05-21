package com.example.fitnessapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/register", "/login", "/static/css", "static/js").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


        return http.build();

    }

//    @Bean
//    public CsrfSec filterChain(HttpSecurity httpSecurity) throws Exception {
//        HttpSecurity
//                //disable CSRF for Postman usage
//                .csrf().disable()
//                //authorize all requests to access CSS and JavaScript
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/static/css", "static/js").permitAll()
//                        //allow all requests to read recipes and reviews
//                        .antMatchers(HttpMethod.GET, "/register", "/login", "/static/css", "static/js").permitAll()
//                        .antMatchers("/generator").hasRole("USER"))
//                .formLogin()
//                .loginPage("/login").permitAll();
//
//        //users should log in with HTTP Basic.
//
//        return httpSecurity.build();
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}