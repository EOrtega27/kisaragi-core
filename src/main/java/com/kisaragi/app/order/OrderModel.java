package com.kisaragi.app.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.OrderProduct.OrderProduct;
import com.kisaragi.app.store.StoreModel;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name="storeId", referencedColumnName = "id")
    private StoreModel storeOrder;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderProduct> orderProducts;

    @Column(name="tracking")
    private String tracking;

    @Column(name="userId")
    private int userId;

    @Column(name="address")
    private String address;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreModel getStoreOrder() {
        return storeOrder;
    }

    public void setStoreOrder(StoreModel storeOrder) {
        this.storeOrder = storeOrder;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
