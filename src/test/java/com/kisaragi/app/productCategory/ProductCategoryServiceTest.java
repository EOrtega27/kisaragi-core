package com.kisaragi.app.productCategory;

import com.kisaragi.app.product.ProductRepository;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryServiceTest {

    @Mock
    ProductCategoryRepository productCategoryRepository;
    @Mock
    StoreService storeService;
    @InjectMocks
    ProductCategoryService productCategoryService;

    private ProductCategoryModel proCategory;
    private StoreModel store;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        store = new StoreModel();
        proCategory = new ProductCategoryModel(
                store,
                "Pollos"
        );
    }

    @Test
    void getAllCategories() {
        Mockito.when(productCategoryRepository.findAllByOrderByIdAsc()).thenReturn(Arrays.asList(proCategory));
        List<ProductCategoryModel> result = productCategoryService.getAllCategories();
        assertEquals(proCategory, result.get(0));
    }

    @Test
    void findById() {
        Mockito.when(productCategoryRepository.findById(ArgumentMatchers.anyInt())).thenReturn(proCategory);
        ProductCategoryModel result = productCategoryService.findById(1);
        assertEquals(proCategory, result);
    }

    @Test
    void getAllByStore() {
        Mockito.when(
                productCategoryRepository.findAllByStoreProductCategory(
                        ArgumentMatchers.any(StoreModel.class)
                )
        ).thenReturn(
                Arrays.asList(proCategory)
        );
        Mockito.when(storeService.findStore(ArgumentMatchers.anyInt())).thenReturn(store);
        final List<ProductCategoryModel> result = productCategoryService.getAllByStore(1);
        assertEquals(result.get(0), proCategory);
    }

    @Test
    void createCategory() {
        Mockito.when(storeService.findStore(1)).thenReturn(store);
        Mockito.when(productCategoryRepository.save(ArgumentMatchers.any(ProductCategoryModel.class))).thenReturn(proCategory);
        ProductCategoryModel result = productCategoryService.createCategory(1,"Pollos");
        assertEquals(result, proCategory);
    }

    @Test
    void existCategory() {
        Mockito.when(productCategoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        boolean result = productCategoryService.existCategory(1);
        assertTrue(result);
    }

    @Test
    void deleteCategory(){
        Mockito.when(productCategoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        boolean result = productCategoryService.deleteCategory(1);
        assertTrue(result);
    }

    @Test
    void deleteCategoryError(){
        Mockito.when(productCategoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        boolean result = productCategoryService.deleteCategory(1);
        assertFalse(false);
    }
}