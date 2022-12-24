package com.kisaragi.app.productCategory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaragi.app.product.ProductController;
import com.kisaragi.app.product.ProductService;
import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.productCategory.SaveProductCategoryRequest;
import com.kisaragi.app.store.StoreModel;
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

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ProductCategoryController.class)
class ProductCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ProductCategoryService proCatService;
    @MockBean
    StoreService storeService;

    ProductCategoryModel productCategoryModel;
    StoreModel storeModel;
    @BeforeEach
    void setUp() {
        storeModel = new StoreModel();
        storeModel.setName("Los pollos hermanos");
        productCategoryModel = new ProductCategoryModel();
        productCategoryModel.setId(1);
        productCategoryModel.setName("Pollos");
        productCategoryModel.setStoreProductCategory(storeModel);
    }

    @Test
    void getAllCategories() throws Exception{
        when(proCatService.getAllCategories()).thenReturn(Arrays.asList(productCategoryModel));
        mockMvc.perform(MockMvcRequestBuilders.get("/product_categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Pollos"));

    }

    @Test
    void getCategoriesByStore() throws Exception{
        when(proCatService.getAllByStore(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(productCategoryModel));
        mockMvc.perform(MockMvcRequestBuilders.post("/product_categories/by_store")
                        .content(asJsonString(new IdRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Pollos"));
    }

    @Test
    void getProductById() throws Exception{
        when(proCatService.findById(ArgumentMatchers.anyInt())).thenReturn(productCategoryModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/product_categories/id")
                        .content(asJsonString(new IdRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pollos"));
    }
    @Test
    void saveCategory() throws Exception{
        SaveProductCategoryRequest request = new SaveProductCategoryRequest();
        request.setStoreId(1);
        request.setProductCategoryName("Pollos");
        when(storeService.existStore(ArgumentMatchers.anyInt())).thenReturn(true);
        when(proCatService.createCategory(1,"Pollos")).thenReturn(productCategoryModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/product_categories/save")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pollos"));
    }
    @Test
    void saveCategoryError() throws Exception{
        SaveProductCategoryRequest request = new SaveProductCategoryRequest();
        request.setStoreId(1);
        request.setProductCategoryName("Pollos");
        when(storeService.existStore(ArgumentMatchers.anyInt())).thenReturn(false);
        when(proCatService.createCategory(1,"Pollos")).thenReturn(productCategoryModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/product_categories/save")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Store Not Found"));
    }

    @Test
    void deleteCategory() throws Exception{
        IdRequest request = new IdRequest(1);
        when(proCatService.deleteCategory(ArgumentMatchers.anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/product_categories/delete")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Product Category Deleted"));
    }

    @Test
    void deleteCategoryError() throws Exception{
        IdRequest request = new IdRequest(1);
        when(proCatService.deleteCategory(1)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/product_categories/delete")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Product Category Not Found"));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}