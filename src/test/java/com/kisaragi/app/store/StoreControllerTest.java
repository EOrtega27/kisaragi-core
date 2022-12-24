package com.kisaragi.app.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaragi.app.product.ProductService;
import com.kisaragi.app.requests.IdRequest;
import com.kisaragi.app.requests.StoreDTO;
import com.kisaragi.app.requests.UpdateStoreRequest;
import com.kisaragi.app.requests.store.AddStoreCategoriesRequest;
import com.kisaragi.app.requests.store.AddStoreCategoryRequest;
import com.kisaragi.app.requests.store.GetByAdminIdRequest;
import com.kisaragi.app.storeCategory.StoreCategoryModel;
import com.kisaragi.app.storeCategory.StoreCategoryService;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(StoreController.class)
class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @MockBean
    private StoreService storeService;

    @MockBean
    private StoreCategoryService stCatService;

    private StoreModel storeTest;

    private StoreCategoryModel category;

    @BeforeEach
    void setUp() {
        storeTest = new StoreModel();
        storeTest.setName("Los Pollos Hermanos");

        category = new StoreCategoryModel();
        category.setName("Libreria");
    }

    @Test
    void getAllStores() throws Exception{
        when(storeService.getAllStores()).thenReturn(Arrays.asList(storeTest));
        mockMvc.perform(MockMvcRequestBuilders.get("/stores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(storeTest.getName()));
    }
    @Test
    void getStoreByAdminId() throws Exception{
        GetByAdminIdRequest request = new GetByAdminIdRequest();
        request.setAdminId("admin");
        when(storeService.findStoreByAdminId(anyString())).thenReturn(Arrays.asList(storeTest));
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/admin_id")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(storeTest.getName()));
    }
    @Test
    void getStore() throws Exception{
        when(storeService.existStore(anyInt())).thenReturn(true);
        when(storeService.findStore(anyInt())).thenReturn(storeTest);
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/id")
                        .content(asJsonString(new IdRequest(0)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.store_data.name").value(storeTest.getName()));
    }
    @Test
    void getStoreError() throws Exception{
        when(storeService.existStore(anyInt())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/id")
                        .content(asJsonString(new IdRequest(0)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No se encontró la tienda"));
    }
    @Test
    void saveStore() throws Exception{
        when(storeService.saveStore(any(StoreModel.class))).thenReturn(storeTest);
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/save")
                        .content(asJsonString(new StoreDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(storeTest.getName()));
    }

    @Test
    void updateStore() throws Exception{
        when(storeService.saveStore(any(StoreModel.class))).thenReturn(storeTest);
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/update")
                        .content(asJsonString(new UpdateStoreRequest(5,new StoreDTO())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(storeTest.getName()));

    }

    @Test
    void deleteStore() throws Exception{
        when(storeService.deleteStore(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/stores/delete")
                        .content(asJsonString(new StoreDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Tienda borrada"));

    }

    @Test
    void deleteStore_error() throws Exception{
        when(storeService.deleteStore(anyInt())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/stores/delete")
                        .content(asJsonString(new StoreDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No se encontró la tienda"));

    }


    @Test
    void addImage() {
    }

    @Test
    void addCategory() throws Exception{
        when(storeService.addCategory(anyInt(),anyInt())).thenReturn(storeTest);
        when(stCatService.findCategory(anyInt())).thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.post("/stores/add_category",1)
                        .content(asJsonString(new AddStoreCategoryRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(storeTest.getName()));
    }

    @Test
    void setCategories() throws Exception{
        AddStoreCategoriesRequest request = new AddStoreCategoriesRequest();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        request.setStoreId(1);
        request.setStoreCategoriesIds(ids);
        when(storeService.addCategories(anyInt(),anyList())).thenReturn(storeTest);

        mockMvc.perform(MockMvcRequestBuilders.post("/stores/set_categories",1)
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(storeTest.getName()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}