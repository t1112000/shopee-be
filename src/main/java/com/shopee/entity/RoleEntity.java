package com.shopee.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean is_deleted = false;

//    @ManyToMany
//    private List<RoleEntity> users;

    public RoleEntity() {}
}
