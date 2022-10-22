package com.shopee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "images")
    private List<String> images;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private Boolean is_deleted=false;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category_id;

//    @ManyToMany
//    private List<CartEntity> carts;
//
//    @ManyToMany
//    private List<BillEntity> bills;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, List<String> images, String price, String description, Boolean is_deleted, CategoryEntity category_id) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.price = price;
        this.description = description;
        this.is_deleted = is_deleted;
        this.category_id = category_id;
    }
}
