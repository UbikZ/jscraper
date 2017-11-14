package org.ubikz.jscraper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.ubikz.jscraper.security.component.AuthProvider;
import org.ubikz.jscraper.security.jwt.JWTAuthenticationFilter;
import org.ubikz.jscraper.security.jwt.JWTLoginFilter;
import org.ubikz.jscraper.security.jwt.model.JWTProperties;

@Configuration
@EnableWebSecurity
public class SecurityService extends WebSecurityConfigurerAdapter {
    public static final String API_AUTHENTICATE = "/api/authenticate";
    @Autowired
    private AuthProvider authProvider;
    @Autowired
    private JWTProperties jwtProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/feed-item").hasRole("USER")
                .antMatchers(HttpMethod.POST, API_AUTHENTICATE).permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(
                        new JWTLoginFilter(API_AUTHENTICATE, authenticationManager(), this.jwtProperties),
                        UsernamePasswordAuthenticationFilter.class
                )
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(this.jwtProperties), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}
