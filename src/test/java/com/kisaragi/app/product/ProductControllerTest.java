package com.kisaragi.app.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaragi.app.productCategory.ProductCategoryRepository;
import com.kisaragi.app.productCategory.ProductCategoryService;
import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.product.SaveProductRequest;
import com.kisaragi.app.requests.product.UpdateProductRequest;
import com.sun.xml.bind.IDResolver;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    ProductCategoryRepository productCategoryRepository;
    @MockBean
    ProductCategoryService productCategoryService;
    ProductModel productModel;

    @BeforeEach
    void setUp(){
        productModel = new ProductModel();
        productModel.setName("Pollo a la Brasa");
    }

    @Test
    void getAllProducts() throws Exception{
        when(productService.getAllProducts()).thenReturn(Arrays.asList(productModel));
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Pollo a la Brasa"));
    }

    @Test
    void getProductById() throws Exception{
        when(productService.getProductById(ArgumentMatchers.anyInt())).thenReturn(productModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/by_id")
                        .content(asJsonString(new IdRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pollo a la Brasa"));
    }

    @Test
    void getProductByCategory() throws Exception{
        when(productService.getAllProductByCategory(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(productModel));
        mockMvc.perform(MockMvcRequestBuilders.post("/products/by_category")
                        .content(asJsonString(new IdRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Pollo a la Brasa"));
    }

    @Test
    void getProductByStore() throws Exception{
        when(productService.getAllProductByStore(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(productModel));
        mockMvc.perform(MockMvcRequestBuilders.post("/products/by_store")
                        .content(asJsonString(new IdRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Pollo a la Brasa"));
    }
    @Test
    void updateProduct() throws Exception{
        UpdateProductRequest request = new UpdateProductRequest();
        request.setId(1);
        request.setName("a");
        request.setStock(23);
        request.setDesciption("aaa");
        request.setUnitPrice(25f);
        request.setCategoryId(1);
        when(productCategoryService.existCategory(ArgumentMatchers.anyInt())).thenReturn(true);
        when(productService.updateProduct(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(productModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/update")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pollo a la Brasa"));
    }
    @Test
    void updateProductError() throws Exception{
        UpdateProductRequest request = new UpdateProductRequest();
        when(productCategoryService.existCategory(ArgumentMatchers.anyInt())).thenReturn(false);
        when(productService.updateProduct(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(productModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/update")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Product Category Not Found"));
    }
    @Test
    void saveProduct() throws Exception{
        SaveProductRequest request = new SaveProductRequest();
        request.setName("a");
        request.setStock(23);
        request.setDescription("aaa");
        request.setUnitPrice(25f);
        request.setCategoryId(1);
        when(productCategoryService.existCategory(ArgumentMatchers.anyInt())).thenReturn(true);
        when(productService.createProduct(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(productModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/save")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pollo a la Brasa"));
    }
    @Test
    void saveProductError() throws Exception{
        SaveProductRequest request = new SaveProductRequest();
        when(productCategoryService.existCategory(ArgumentMatchers.anyInt())).thenReturn(false);
        when(productService.createProduct(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(productModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/save")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Product Category Not Found"));
    }
    @Test
    void deleteProduct() throws Exception{
        IdRequest request = new IdRequest(1);
        when(productService.existsProduct(ArgumentMatchers.anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Product Deleted"));
    }
    @Test
    void deleteProductError() throws Exception{
        IdRequest request = new IdRequest(1);
        when(productService.existsProduct(ArgumentMatchers.anyInt())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Product Not Found"));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}