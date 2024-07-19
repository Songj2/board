package com.NC13.studyBoard.config;

import com.NC13.studyBoard.service.UsersCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
UsersCustomService usersCustomService;
@Autowired
private AuthenticationFailureHandler customFailureHandler;
@Bean
public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    HttpSessionRequestCache requestCache= new HttpSessionRequestCache();
    requestCache.setMatchingRequestParameterName(null);
    httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .requestCache(request -> request.requestCache(requestCache))
            .authorizeHttpRequests(authorize->
                    authorize.requestMatchers("/", "/user/*","/login", "/register", "/error").permitAll()
                            .requestMatchers("favicon.ico").anonymous()
                            .anyRequest().authenticated()
            )
            .formLogin((formLogin)->
                    formLogin.loginPage("/user/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .loginProcessingUrl("/user/auth")
//                            .failureForwardUrl("/user/login")
                            .failureHandler(customFailureHandler)
            )
            .userDetailsService(usersCustomService);

    return httpSecurity.build();
}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationFailureHandler customAuthFailureHandler(){
    return new CustomFailureHandler();
    }
}
