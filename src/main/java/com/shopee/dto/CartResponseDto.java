package com.shopee.dto;

import com.shopee.entity.CartItemEntity;
import com.shopee.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class CartResponseDto {
    private Long id;

    private UserEntity user;

    private Boolean is_deleted = false;

    private double total = 0;

    private int quantity = 0;

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
        this.user = user;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
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

    public List<CartItemEntity> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<CartItemEntity> cart_items) {
        this.cart_items = cart_items;
    }
}
