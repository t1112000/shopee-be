package com.shopee.service.impl;

import com.shopee.entity.UserEntity;
import com.shopee.repository.UserRepository;
import com.shopee.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> foundUser = userRepository.findByEmail(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); // use list if you wish
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + foundUser.get().getRole().getName()));

        return new User(foundUser.get().getEmail(), foundUser.get().getPassword(), grantedAuthorities);
    }
}
