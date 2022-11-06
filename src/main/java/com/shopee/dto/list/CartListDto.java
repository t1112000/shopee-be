package com.shopee.dto.list;

import com.shopee.entity.CartEntity;

import java.util.ArrayList;
import java.util.List;

public class CartListDto {
    private int total = 0;

    private List<CartEntity> cart_list = new ArrayList<>();

    public CartListDto(int total, List<CartEntity> cart_list) {
        this.total = total;
        this.cart_list = cart_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CartEntity> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartEntity> cart_list) {
        this.cart_list = cart_list;
    }
}
