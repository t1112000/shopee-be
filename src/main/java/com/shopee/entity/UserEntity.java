package com.shopee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "is_deleted")
    private Boolean is_deleted = false;

    @ManyToMany
    private List<RoleEntity> roles;

    @OneToMany
    private List<CartEntity> carts;

    @OneToMany
    private List<BillEntity> bills;

    public UserEntity() {}
}
