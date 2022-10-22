package com.shopee.service.impl;

import com.shopee.entity.UserEntity;
import com.shopee.repository.UserRepository;
import com.shopee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAllByIs_deletedFalse();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity update(Long id,UserEntity user) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setUpdated_at(new Date());
        userEntity.setAddress((user.getAddress()));
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setIs_deleted(true);
        userRepository.save(userEntity);
    }
}
