package com.kisaragi.app.order;

import com.kisaragi.app.OrderProduct.OrderProduct;
import com.kisaragi.app.OrderProduct.OrderProductRepository;
import com.kisaragi.app.product.ProductModel;
import com.kisaragi.app.product.ProductService;
import com.kisaragi.app.requests.order.OrderResponse;
import com.kisaragi.app.requests.order.ProductsInOrderDTO;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    StoreService storeService;
    @Mock
    ProductService productService;
    @Mock
    OrderProductRepository orderProductRepository;
    @InjectMocks
    OrderService orderService;

    OrderModel order;
    StoreModel store;
    ProductModel p1;
    ProductModel p2;
    OrderProduct orderProduct;
    OrderResponse orderResponse;
    List<ProductsInOrderDTO> products;
    Set<OrderProduct> op;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        order = new OrderModel();
        order.setId(1);
        store = new StoreModel();
        p1 = new ProductModel();
        p1 = new ProductModel();
        products = new ArrayList<>();
        products.add(new ProductsInOrderDTO(1,2));
        orderProduct = new OrderProduct(order,p1,5);
        op = new HashSet<>();
        op.add(orderProduct);
        order.setOrderProducts(op);
        orderResponse = new OrderResponse();
        orderResponse.setTracking("trackingTest123");
    }

    @Test
    void createOrder() {
        Mockito.when(storeService.findStore(1)).thenReturn(store);
        Mockito.when(orderRepository.findByTracking("tra")).thenReturn(order);
        Mockito.when(productService.getProductById(1)).thenReturn(p1);
        Mockito.when(orderProductRepository.save(ArgumentMatchers.any(OrderProduct.class))).thenReturn(orderProduct);
        Mockito.when(orderRepository.save(ArgumentMatchers.any(OrderModel.class))).thenReturn(order);
        OrderModel result = orderService.createOrder(1, "tra", "user", "av", products, 20f);
        assertEquals(result.getStoreOrder(),store);
    }


    @Test
    void updateState() {
        Mockito.when(orderRepository.findByTracking("tra")).thenReturn(order);
        Mockito.when(orderRepository.save(ArgumentMatchers.any(OrderModel.class))).thenReturn(order);
        OrderModel result = orderService.updateState("tra",State.ENTREGADA);
        assertEquals(result.getState(),State.ENTREGADA);
        assertEquals(result.getId(),order.getId());
    }


    @Test
    void addOrderProduct() {
        Mockito.when(orderRepository.findByTracking("tra")).thenReturn(order);
        Mockito.when(productService.getProductById(1)).thenReturn(p1);
        Mockito.when(orderProductRepository.save(ArgumentMatchers.any(OrderProduct.class))).thenReturn(orderProduct);
        OrderProduct result = orderService.addOrderProduct("tra",1,5);
        assertEquals(result, orderProduct);

    }
}