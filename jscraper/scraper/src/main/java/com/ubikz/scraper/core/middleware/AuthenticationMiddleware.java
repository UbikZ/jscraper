package com.ubikz.scraper.core.middleware;

import com.ubikz.scraper.api.controller.filter.UserFilterBody;
import com.ubikz.scraper.core.app.context.UserContext;
import com.ubikz.scraper.core.app.dto.UserDto;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationMiddleware implements AuthenticationProvider {
    private UserContext userContext;

    @Autowired
    public AuthenticationMiddleware(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = null;

        UserFilterBody userFilterBody = new UserFilterBody();
        userFilterBody.setUsername(authentication.getName());
        userFilterBody.setPassword((String) authentication.getCredentials());

        BaseMessage msg = this.userContext.getAll(userFilterBody);
        List<UserDto> userList = (List<UserDto>) msg.getData();

        if (userList != null && userList.size() == 1) {
            UserDto user = userList.get(0);

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (user.getAdmin()) {
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuths);
        }

        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
