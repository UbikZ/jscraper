package org.ubikz.jscraper.api.service.model.filter.impl;

import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;

public class UserServiceFilter extends AbstractServiceFilter {
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserServiceFilter{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + "} " + super.toString();
    }
}