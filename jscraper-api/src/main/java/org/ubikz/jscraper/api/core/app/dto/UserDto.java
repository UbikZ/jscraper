package org.ubikz.jscraper.api.core.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto extends AbstractDto {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Boolean isAdmin;

    @JsonIgnore
    @Override
    public String getLabel() {
        return super.getLabel();
    }

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}