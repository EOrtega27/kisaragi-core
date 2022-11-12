package com.kisaragi.app.storeCategory;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.*;
@WebMvcTest(StoreCategoryController.class)
class StoreCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreCategoryService stCatService;

    private StoreCategoryModel stCat;

    @BeforeEach
    void setUp() {
        stCat = new StoreCategoryModel();
        stCat.setName("test");
    }

    @Test
    void getAllStoreCategories() throws Exception{
        when(stCatService.findAll()).thenReturn(Arrays.asList(stCat));
        mockMvc.perform(MockMvcRequestBuilders.get("/store_categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("test"));
    }

    @Test
    void getStoreCategory() {
    }

    @Test
    void saveStoreCategory() {
    }

    @Test
    void updateStoreCategory() {
    }

    @Test
    void deleteStoreCategory() {
    }
}