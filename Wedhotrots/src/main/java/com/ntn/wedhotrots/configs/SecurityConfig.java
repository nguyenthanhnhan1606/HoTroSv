package com.ntn.wedhotrots.configs;

import com.ntn.wedhotrots.components.CustomAccessDeniedHandler;
import com.ntn.wedhotrots.components.JwtTokenAuthenticationFilter;
import com.ntn.wedhotrots.components.RestAuthenticationEntryPoint;
import com.ntn.wedhotrots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserService userSer;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/api/register").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/**").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/api/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN")
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userSer);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
