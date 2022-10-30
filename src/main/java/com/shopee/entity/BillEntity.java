package com.shopee.entity;

import javax.persistence.*;

@Entity
@Table(name = "bills")
public class BillEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

//    @Column(name = "total")
//    private double total;

//    @Column(name = "quantity")
//    private int quantity;
//
//    @ManyToMany
//    private List<ProductEntity> products = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
