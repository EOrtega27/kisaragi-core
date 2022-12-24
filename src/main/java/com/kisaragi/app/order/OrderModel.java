package com.kisaragi.app.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.OrderProduct.OrderProduct;
import com.kisaragi.app.store.StoreModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name="storeId", referencedColumnName = "id")
    private StoreModel storeOrder;

    @OneToMany(mappedBy = "orderId")
    @JsonIgnore
    private Set<OrderProduct> orderProducts;

    @Column(name="tracking", unique = true)
    private String tracking;

    @Column(name="userId")
    private String userId;

    @Column(name="address")
    private String address;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name="total")
    private float total;

    public OrderModel() {
        super();
    }

    public OrderModel(StoreModel storeOrder, String tracking, String userId, String address) {
        this.storeOrder = storeOrder;
        this.tracking = tracking;
        this.userId = userId;
        this.address = address;
        this.state = State.NOTIFICADA;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
