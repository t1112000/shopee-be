package com.shopee.dto.list;

import com.shopee.entity.CartEntity;

import java.util.ArrayList;
import java.util.List;

public class CartListDto {
    private int total = 0;

    private List<CartEntity> carts = new ArrayList<>();

    public CartListDto(int total, List<CartEntity> carts) {
        this.total = total;
        this.carts = carts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }
}
