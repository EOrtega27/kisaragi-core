package com.kisaragi.app.order;

import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.order.CreateOrderRequest;
import com.kisaragi.app.requests.order.GetOrderByTracking;
import com.kisaragi.app.requests.order.GetOrdersByUserId;
import com.kisaragi.app.requests.order.UpdateOrderState;
import com.kisaragi.app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    StoreService storeService;

    @GetMapping(" ")
    private ResponseEntity<Object> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<Object> saveOrder(@RequestBody CreateOrderRequest request){
        return new ResponseEntity<>(orderService.createOrder(request.getStoreId(),request.getTracking(), request.getUserId(), request.getAddress(), request.getProducts()),HttpStatus.OK);
    }

    @PostMapping("/by_store")
    private ResponseEntity<Object> getOrdersByStore(@RequestBody IdRequest request){
        if(storeService.existStore(request.getId())){
            return new ResponseEntity<>(orderService.getOrdersByStore(request.getId()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Store Not Found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/by_user")
    private ResponseEntity<Object> getOrdersByUser(@RequestBody GetOrdersByUserId request){
        return new ResponseEntity<>(orderService.getOrdersByUser(request.getUserId()),HttpStatus.OK);
    }

    @PostMapping("/by_tracking")
    private ResponseEntity<Object> getOrdersByTracking(@RequestBody GetOrderByTracking request){
        return new ResponseEntity<>(orderService.getByTracking(request.getTracking()),HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<Object> updateOrderState(@RequestBody UpdateOrderState request){
        return new ResponseEntity<>(orderService.updateState(request.getTracking(), request.getState()),HttpStatus.OK);
    }
}
