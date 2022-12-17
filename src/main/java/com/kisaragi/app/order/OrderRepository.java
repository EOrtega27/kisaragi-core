package com.kisaragi.app.order;

import com.kisaragi.app.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
    public OrderModel findById(int id);
    public List<OrderModel> findAllByStoreOrder(StoreModel storeOrder);
    public List<OrderModel> findAllByUserId(String userId);
    public OrderModel findByTracking(String tracking);
}
