package com.kisaragi.app.storeCategory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaragi.app.dto.IdRequest;
import com.kisaragi.app.dto.StoreCategoryDTO;
import com.kisaragi.app.dto.UpdateStoreCategoryRequest;
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
    void getStoreCategory() throws Exception{
        when(stCatService.findCategory(0)).thenReturn(stCat);
        mockMvc.perform(MockMvcRequestBuilders.get("/store_categories/id")
                        .content(asJsonString(new IdRequest(0)))
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
    }

    @Test
    void saveStoreCategory() throws Exception{
        when(stCatService.saveCategory(any(StoreCategoryModel.class))).thenReturn(stCat);
        mockMvc.perform(MockMvcRequestBuilders.post("/store_categories/save")
                .content(asJsonString(new StoreCategoryDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(stCat.getName()));
    }

    @Test
    void updateStoreCategory() throws Exception{
        when(stCatService.saveCategory(any(StoreCategoryModel.class))).thenReturn(stCat);
        mockMvc.perform(MockMvcRequestBuilders.post("/store_categories/update")
                        .content(asJsonString(new UpdateStoreCategoryRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(stCat.getName()));
    }

    @Test
    void deleteStoreCategory() throws Exception{
        when(stCatService.deleteCategory(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/store_categories/delete")
                        .content(asJsonString(new IdRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Categoría borrada"));
    }

    @Test
    void deleteStoreCategory_error() throws Exception{
        when(stCatService.deleteCategory(anyInt())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/store_categories/delete")
                        .content(asJsonString(new IdRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No se encontró la categoria"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}