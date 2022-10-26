package com.shopee.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull.List({@NotNull(message = "images must be array")})
    private List<String> images = new ArrayList<>();

    @NotNull(message = "price is required")
    @Min(value = 0, message = "the value must be equal or greater than 0")
    private Double price;

    private String description;

    @NotNull(message = "category_id is required")
    private Long category_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
