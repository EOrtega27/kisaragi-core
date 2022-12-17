package com.kisaragi.app.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class CreateOrders implements CommandLineRunner {
    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        orderService.createOrder(
                1,
                "trackingTest123",
                "testUser123",
                "Av. Argentina"
        );

        orderService.createOrder(
                1,
                "trackingTest456",
                "testUser123",
                "Av. Argentina"
        );

        orderService.createOrder(
                2,
                "trackingTest789",
                "testUser123",
                "Av. Argentina"
        );

        orderService.addOrderProduct("trackingTest123",1,5);
        orderService.addOrderProduct("trackingTest123",2,10);
        orderService.addOrderProduct("trackingTest789",4,2);
    }
}
