package com.shopee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "token")
    private String token;

    @Column(name = "is_deleted")
    private Boolean is_deleted = false;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private RoleEntity role;

//    @OneToMany
//    private List<CartEntity> carts = new ArrayList<>();
//
//    @OneToMany
//    private List<BillEntity> bills = new ArrayList<>();

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null) {
            this.address = address;
        }
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        if(phone_number !=null){
            this.phone_number = phone_number;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if(token != null){
            this.token = token;
        }
    }
}