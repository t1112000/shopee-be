package com.shopee.dto.list;

import com.shopee.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDto {
    private int total = 0;

    private List<CategoryEntity> categories = new ArrayList<>();

    public CategoryListDto(int total, List<CategoryEntity> categories) {
        this.total = total;
        this.categories = categories;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
