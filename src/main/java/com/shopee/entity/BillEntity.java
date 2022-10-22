package com.shopee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bills")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    @Column(name = "total")
    private double total;

    @Column(name = "quantity")
    private int quantity;

    @ManyToMany
    private List<ProductEntity> products;

    @ManyToOne
    private UserEntity user;

    public BillEntity() {}

    public BillEntity(Long id, double total, int quantity, List<ProductEntity> products) {
        this.id = id;
        this.total = total;
        this.quantity = quantity;
        this.products = products;
    }
}
