package com.shopee.dto.list;

import com.shopee.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDto {
    private int total = 0;

    private List<CategoryEntity> category_list = new ArrayList<>();

    public CategoryListDto(int total, List<CategoryEntity> category_list) {
        this.total = total;
        this.category_list = category_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CategoryEntity> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(List<CategoryEntity> category_list) {
        this.category_list = category_list;
    }
}
