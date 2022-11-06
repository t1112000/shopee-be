package com.shopee.dto.list;

import com.shopee.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductListDto {
    private int total = 0;

    private List<ProductEntity> product_list = new ArrayList<>();

    public ProductListDto(int total, List<ProductEntity> product_list) {
        this.total = total;
        this.product_list = product_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductEntity> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<ProductEntity> product_list) {
        this.product_list = product_list;
    }
}
