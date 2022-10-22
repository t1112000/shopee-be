package com.shopee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany
    @Column(name = "products")
    private List<ProductEntity> products;

    public CategoryEntity(){}

}
