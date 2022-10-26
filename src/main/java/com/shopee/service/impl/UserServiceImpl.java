package com.shopee.service.impl;

import com.shopee.dto.ChangePasswordDto;
import com.shopee.dto.UserDto;
import com.shopee.entity.ResponseObject;
import com.shopee.entity.RoleEntity;
import com.shopee.entity.UserEntity;
import com.shopee.repository.RoleRepository;
import com.shopee.repository.UserRepository;
import com.shopee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query users successfully", userRepository.findAllByIs_deletedFalse()));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<UserEntity> foundUser = userRepository.findById(id);
        // Found user
        if (foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query user successfully", foundUser));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find user with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> signIn(UserDto user) {
        Optional<UserEntity> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent() && bCryptPasswordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Login successfully", foundUser));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Email or password is wrong"));
    }

    @Override
    public ResponseEntity<ResponseObject> updatePassword(ChangePasswordDto user) {
        Optional<UserEntity> foundUser = userRepository.findByEmail(user.getEmail());

        if (foundUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Email is not exist"));
        }

        if (!Objects.equals(user.getPassword(), user.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Current password and Confirm Password are not the same"));
        }

        if (Objects.equals(user.getNewPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "New password must not be the same as the current password"));
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Current password is wrong"));
        }

        foundUser.get().setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
        foundUser.get().setUpdated_at(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Change password successfully", userRepository.save(foundUser.get())));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, UserDto newUser) {
        Optional<UserEntity> foundUser = userRepository.findByEmail(newUser.getEmail());

        // Check email is already
        if (foundUser.isPresent() && foundUser.get().getId() != id) {
            Map<String, String> validationError = new HashMap<>();
            validationError.put("email", "email is already");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, validationError));
        }

        Optional<UserEntity> userEntity = userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setUpdated_at(new Date());
            user.setAddress((newUser.getAddress()));

            return userRepository.save(user);
        });

        // Found user
        if (userEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false, "Updated user successfully", userEntity));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find user with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(UserDto newUser) {
        Optional<UserEntity> foundUser = userRepository.findByEmail(newUser.getEmail());

        // Check email is already
        if (foundUser.isPresent()) {
            Map<String, String> validationError = new HashMap<>();
            validationError.put("email", "email is already");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, validationError));
        }

        // Create new role User List
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleRepository.findAllByName("USER"));

        // Create new User
        UserEntity user = new UserEntity();
        user.setName(newUser.getName());
        user.setAddress(newUser.getAddress());
        user.setEmail(newUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setPhone_number(newUser.getPhone_number());
        user.setRoles(roles);


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created user successfully", userRepository.save(user)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<UserEntity> userEntity = userRepository.findByIdAndIs_deletedFalse(id).map(user -> {
            user.setIs_deleted(true);
            user.setUpdated_at(new Date());
            return userRepository.save(user);
        });

        if (userEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted user successfully"));
        }


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false, "Cannot find user with id=" + id));
    }
}
