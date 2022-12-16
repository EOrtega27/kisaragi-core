package com.kisaragi.app.OrderProduct;

import com.kisaragi.app.order.OrderModel;
import com.kisaragi.app.product.ProductModel;

import javax.persistence.*;

@Entity
public class OrderProduct {
    @EmbeddedId
    OrderProductKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    OrderModel order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    ProductModel product;

    int quantity;

    public OrderProductKey getId() {
        return id;
    }

    public void setId(OrderProductKey id) {
        this.id = id;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
