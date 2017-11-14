package org.ubikz.jscraper.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.ubikz.jscraper.security.CORSSecurityService;
import org.ubikz.jscraper.security.model.AccountCredentials;
import org.ubikz.jscraper.security.jwt.model.JWTProperties;
import org.ubikz.jscraper.security.component.TokenAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private JWTProperties jwtProperties;

    public JWTLoginFilter(String url, AuthenticationManager authManager, JWTProperties jwtProperties) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.jwtProperties = jwtProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        CORSSecurityService.handleHeaders(request, response);

        AccountCredentials creds = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthentication.addAuthentication(res, auth, this.jwtProperties);
    }
}
