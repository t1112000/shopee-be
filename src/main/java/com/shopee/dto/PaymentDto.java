package com.shopee.dto;

import javax.validation.constraints.NotNull;

public class PaymentDto {
    @NotNull(message = "user_id is required")
    private Long user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
