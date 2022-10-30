package com.shopee.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "total")
    private double total = 0;

    @Column(name = "quantity")
    private int quantity = 0;

    @Column(name = "is_deleted")
    private Boolean is_deleted = false;

    @OneToMany
    private List<CartItemEntity> cart_items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        if (user != null) {
            this.user = user;
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<CartItemEntity> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<CartItemEntity> cart_items) {
        if (cart_items != null) {
            this.cart_items = cart_items;
        }
    }
}
