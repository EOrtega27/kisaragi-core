package com.kisaragi.app.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.order.*;
import com.kisaragi.app.requests.product.UpdateProductRequest;
import com.kisaragi.app.store.StoreService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    OrderService orderService;
    @MockBean
    StoreService storeService;

    OrderModel orderModel;
    OrderResponse orderResponse;
    @BeforeEach
    void setUp() {
        orderModel = new OrderModel();
        orderModel.setId(1);
        orderResponse = new OrderResponse();
        orderResponse.setOrder_id(1);
    }

    @Test
    void getAllOrders() throws Exception{
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(orderResponse));
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].order_id").value(1));


    }

    @Test
    void getOrdersByUser() throws Exception{
        GetOrdersByUserId request = new GetOrdersByUserId();
        request.setUserId("user");
        when(orderService.getOrdersByUser(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(orderResponse));
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/by_user")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].order_id").value(1));
    }
    @Test
    void getOrdersByStore() throws Exception{
        IdRequest request = new IdRequest(1);
        when(storeService.existStore(anyInt())).thenReturn(true);
        when(orderService.getOrdersByStore(anyInt())).thenReturn(Arrays.asList(orderResponse));
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/by_store")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].order_id").value(1));
    }
    @Test
    void getOrdersByStoreError() throws Exception{
        IdRequest request = new IdRequest(1);
        when(storeService.existStore(anyInt())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/by_store")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Store Not Found"));
    }
    @Test
    void getOrdersByTracking() throws Exception{
        GetOrderByTracking request = new GetOrderByTracking();
        request.setTracking("track");
        when(orderService.getByTracking(ArgumentMatchers.anyString())).thenReturn(orderResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/by_tracking")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.order_id").value(1));
    }
    @Test
    void updateOrder() throws Exception{
        UpdateOrderState request = new UpdateOrderState();
        request.setTracking("Tracking");
        request.setState(State.ENTREGADA);
        when(orderService.updateState("Tracking", State.ENTREGADA)).thenReturn(orderModel);
        mockMvc.perform(MockMvcRequestBuilders.put("/orders/update")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    void saveOrder() throws Exception{
        List<ProductsInOrderDTO> list = new ArrayList<>();
        CreateOrderRequest request = new CreateOrderRequest();
        request.setUserId("User");
        request.setStoreId(1);
        request.setTotal(15f);
        request.setProducts(list);
        request.setTracking("Track");
        request.setAddress("Address");
        when(orderService.createOrder(
                 1,
                "Track",
                "User",
                "Address",
                list,
                15f
                )
        ).thenReturn(orderModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/save")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}