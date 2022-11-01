package com.shopee.dto;

import javax.validation.constraints.NotNull;

public class PaymentDto {
    @NotNull(message = "cart_id is required")
    private Long cart_id;

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }
}
