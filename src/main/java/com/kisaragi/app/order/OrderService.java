package com.kisaragi.app.order;

import com.kisaragi.app.OrderProduct.OrderProduct;
import com.kisaragi.app.OrderProduct.OrderProductRepository;
import com.kisaragi.app.requests.order.GetOrderProductsResponse;
import com.kisaragi.app.requests.order.OrderResponse;
import com.kisaragi.app.product.ProductModel;
import com.kisaragi.app.product.ProductService;
import com.kisaragi.app.requests.order.ProductsInOrderDTO;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderProductRepository orderProductRepository;
    public OrderModel createOrder(int storeId, String tracking, String userId, String address, List<ProductsInOrderDTO> products, float total){
        StoreModel store = storeService.findStore(storeId);
        OrderModel newOrder = new OrderModel(
                store,
                tracking,
                userId,
                address
        );
        newOrder.setTotal(total);
        orderRepository.save(newOrder);
        for (ProductsInOrderDTO p: products){
            addOrderProduct(tracking, p.getIdProduct(), p.getQuantity());
        }
        return newOrder;
    }

    public OrderResponse getByTracking(String tracking){
        OrderModel order =  orderRepository.findByTracking(tracking);
        return toResponse(order);
    }

    public OrderResponse toResponse(OrderModel order){
        OrderResponse response = new OrderResponse();
        response.setAddress(order.getAddress());
        response.setTracking(order.getTracking());
        response.setOrder_id(order.getId());
        response.setStore_name(order.getStoreOrder().getName());
        response.setState(order.getState());
        response.setUserId(order.getUserId());
        response.setTotal(order.getTotal());
        List<OrderProduct> op = orderProductRepository.findAllByOrderId(order);
        List<GetOrderProductsResponse> products = new ArrayList<>();
        for (OrderProduct p: op){
            GetOrderProductsResponse newProduct = new GetOrderProductsResponse(
                    p.getProductId().getId(),
                    p.getProductId().getName(),
                    p.getQuantity()
            );
            products.add(newProduct);
        }
        response.setProducts(products);
        return response;
    }
    public OrderModel updateState(String tracking, State state){
        OrderModel order = orderRepository.findByTracking(tracking);
        order.setState(state);
        return orderRepository.save(order);
    }
    public List<OrderResponse> getAllOrders(){
        List<OrderModel> orders = orderRepository.findAll();
        List<OrderResponse> response = new ArrayList<>();
        for (OrderModel o: orders){
            response.add(toResponse(o));
        }
        return response;
    }

    public List<OrderResponse> getOrdersByStore(int storeId){
        StoreModel store = storeService.findStore(storeId);
        List<OrderModel> orders = orderRepository.findAllByStoreOrder(store);
        List<OrderResponse> response = new ArrayList<>();
        for (OrderModel o: orders){
            response.add(toResponse(o));
        }
        return response;
    }

    public List<OrderResponse> getOrdersByUser(String userId){
        List<OrderModel> orders = orderRepository.findAllByUserId(userId);
        List<OrderResponse> response = new ArrayList<>();
        for (OrderModel o: orders){
            response.add(toResponse(o));
        }
        return response;
    }

    public OrderProduct addOrderProduct(String tracking, int productId, int quantity){
        OrderModel order = orderRepository.findByTracking(tracking);
        ProductModel product = productService.getProductById(productId);
        OrderProduct newOP = new OrderProduct(
                order,
                product,
                quantity
        );
        return orderProductRepository.save(newOP);
    }
}
