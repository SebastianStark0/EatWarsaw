package org.example.eatwarsaw.config.services;

import org.example.eatwarsaw.model.user.CustomUserDetails;
import org.example.eatwarsaw.model.user.User;
import org.example.eatwarsaw.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword());
    }


}
