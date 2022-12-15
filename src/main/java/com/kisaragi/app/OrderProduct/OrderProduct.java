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

}
