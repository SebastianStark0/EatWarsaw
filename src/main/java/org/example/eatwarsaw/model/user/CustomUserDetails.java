package org.example.eatwarsaw.model.user;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    @Getter
    private final Long id;
    private final String email;
    private final String password;

    public CustomUserDetails(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static CustomUserDetails build(User user) {
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // або роли, якщо є
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
