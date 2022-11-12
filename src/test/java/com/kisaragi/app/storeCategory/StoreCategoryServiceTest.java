package com.kisaragi.app.storeCategory;

import com.kisaragi.app.store.StoreModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StoreCategoryServiceTest {

    @Mock
    StoreCategoryRepository stCatRepository;

    @InjectMocks
    StoreCategoryService stCatService;

    private StoreCategoryModel stCat;
    private StoreModel store01;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        stCat = new StoreCategoryModel(0,"Librería");
        store01 = new StoreModel();
        Set<StoreModel> stores = new HashSet<>(Arrays.asList(store01));
        stCat.setStores(stores);
    }

    @Test
    void findCategory() {
        when(stCatRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        when(stCatRepository.findById(ArgumentMatchers.anyInt())).thenReturn(stCat);
        final StoreCategoryModel actual = stCatService.findCategory(5);
        assertEquals(actual.getName(),stCat.getName());
        assertEquals(actual.getId(),stCat.getId());
        Mockito.verify(stCatRepository, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
    }

    @Test
    void findCategory_error() {
        Mockito.when(stCatRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        final StoreCategoryModel actual = stCatService.findCategory(5);
        assertNull(actual);
        Mockito.verify(stCatRepository, Mockito.times(1)).existsById(ArgumentMatchers.anyInt());
    }

    @Test
    void findAll() {
        Mockito.when(stCatRepository.findAllByOrderById()).thenReturn(Arrays.asList(stCat));
        final List<StoreCategoryModel> actual = stCatService.findAll();
        assertEquals(actual.get(0).getName(),stCat.getName());
        Mockito.verify(stCatRepository, Mockito.times(1)).findAllByOrderById();
        Mockito.verifyNoMoreInteractions(stCatRepository);
    }

    @Test
    void saveCategory() {
        Mockito.when(stCatRepository.save(ArgumentMatchers.any(StoreCategoryModel.class))).thenReturn(stCat);
        assertNotNull(stCatService.saveCategory(stCat));
        Mockito.verify(stCatRepository, Mockito.times(1)).save(ArgumentMatchers.any(StoreCategoryModel.class));
        Mockito.verifyNoMoreInteractions(stCatRepository);
    }

    @Test
    void deleteCategory() {
        Mockito.when(stCatRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        assertTrue(stCatService.deleteCategory(5));
    }

    @Test
    void existCategory() {
        Mockito.when(stCatRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        final boolean actual = stCatService.existCategory(5);
        assertTrue(actual);
        Mockito.verify(stCatRepository, Mockito.times(1)).existsById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(stCatRepository);
    }

    @Test
    void getStoresById(){
        Mockito.when(stCatRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        Mockito.when(stCatRepository.findById(ArgumentMatchers.anyInt())).thenReturn(stCat);
        Set<StoreModel> actualStores = stCatService.getStoresById(ArgumentMatchers.anyInt());
        assertEquals(actualStores.size(),1);
    }
}