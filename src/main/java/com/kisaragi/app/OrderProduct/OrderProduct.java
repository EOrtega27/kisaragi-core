package com.kisaragi.app.OrderProduct;

import com.kisaragi.app.order.OrderModel;
import com.kisaragi.app.product.ProductModel;

import javax.persistence.*;

@Entity
@Table(name = "OrdersProducts")
@IdClass(OrderProductKey.class)
public class OrderProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private OrderModel orderId;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private ProductModel productId;

    @Column(name="quantity")
    int quantity;

    public OrderProduct() {
    }

    public OrderProduct(OrderModel orderId, ProductModel productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderModel getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderModel orderId) {
        this.orderId = orderId;
    }

    public ProductModel getProductId() {
        return productId;
    }

    public void setProductId(ProductModel productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
