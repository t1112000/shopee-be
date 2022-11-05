package com.shopee.service.impl;

import com.shopee.entity.UserEntity;
import com.shopee.repository.UserRepository;
import com.shopee.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsServiceImp implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> foundUser = userRepository.findByEmail(email);
        return new User(foundUser.get().getEmail(), foundUser.get().getPassword(), new ArrayList<>());
    }
}
