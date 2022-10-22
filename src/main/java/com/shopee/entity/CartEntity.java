package com.shopee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "user",nullable = false)
    private UserEntity user;

    @Column(name = "total")
    private double total;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_deleted")
    private Boolean is_deleted=false;

    @ManyToMany
    private List<ProductEntity> products;

    public CartEntity() {}
}
