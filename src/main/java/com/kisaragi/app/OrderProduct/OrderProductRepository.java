package com.kisaragi.app.OrderProduct;

import com.kisaragi.app.order.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    public List<OrderProduct> findAllByOrderId(OrderModel orderId);
}
