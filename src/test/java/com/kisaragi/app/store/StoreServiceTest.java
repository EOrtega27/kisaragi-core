package com.kisaragi.app.store;

import com.kisaragi.app.storeCategory.StoreCategoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreService storeService;

    private StoreModel store;

    private StoreCategoryModel category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        store = new StoreModel();
        store.setName("Los Pollos Hermanos");
        category = new StoreCategoryModel(1,"Polleria");
        HashSet<StoreCategoryModel> hashSet = new HashSet<StoreCategoryModel>();
        hashSet.add(category);
        store.setStoreCategories(hashSet);
    }

    @Test
    void findStore() {
        when(storeRepository.findById(anyInt())).thenReturn(store);
        final StoreModel result = storeService.findStore(5);
        assertEquals(result.getName(),store.getName());
        assertEquals(result.getId(),store.getId());
    }

    @Test
    void getAllStores() {
        when(storeRepository.findAllByOrderByIdAsc()).thenReturn(Arrays.asList(store));
        final List<StoreModel> result = storeService.getAllStores();
        assertEquals(result.get(0).getName(), store.getName());
    }

    @Test
    void saveStore() {
        when(storeRepository.save(any(StoreModel.class))).thenReturn(store);
        final StoreModel result = storeService.saveStore(store);
        assertNotNull(result);
    }

    @Test
    void updateStore() {
        when(storeRepository.save(any(StoreModel.class))).thenReturn(store);
        final StoreModel result = storeService.updateStore(store);
        assertNotNull(result);
    }

    @Test
    void deleteStore() {
        when(storeRepository.existsById(anyInt())).thenReturn(true);
        final boolean result = storeService.deleteStore(5);
        assertTrue(result);
    }

    @Test
    void existStore() {
        when(storeRepository.existsById(anyInt())).thenReturn(true);
        final boolean result = storeService.existStore(5);
        assertTrue(result);
    }

}