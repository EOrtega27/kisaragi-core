package com.kisaragi.app.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.OrderProduct.OrderProduct;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne()
    @JoinColumn(name="storeId", referencedColumnName = "id")
    public int storeOrder;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    Set<OrderProduct> orderProducts;
}
