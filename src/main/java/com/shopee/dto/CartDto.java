package com.shopee.dto;

import javax.validation.constraints.NotNull;

public class CartDto {

    @NotNull(message = "product_id is required")
    private Long product_id;

    private Long user_id;

    private int quantity = 1;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
