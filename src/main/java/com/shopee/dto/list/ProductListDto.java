package com.shopee.dto.list;

import com.shopee.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductListDto {
    private int total = 0;

    private List<ProductEntity> products = new ArrayList<>();

    public ProductListDto(int total, List<ProductEntity> products) {
        this.total = total;
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
