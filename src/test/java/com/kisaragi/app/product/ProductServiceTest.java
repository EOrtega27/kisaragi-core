package com.kisaragi.app.product;

import com.kisaragi.app.productCategory.ProductCategoryModel;
import com.kisaragi.app.productCategory.ProductCategoryRepository;
import com.kisaragi.app.productCategory.ProductCategoryService;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreRepository;
import com.kisaragi.app.store.StoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductCategoryRepository productCategoryRepository;
    @Mock
    ProductCategoryService productCategoryService;
    @InjectMocks
    ProductService productService;


    private ProductModel product;
    private StoreModel store;
    private ProductCategoryModel productCategory;
    private ProductCategoryModel productCategory2;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        store=new StoreModel();
        store.setName("Los Pollos Hermanos");
        productCategory = new ProductCategoryModel(
                store,
                "Pollos"
        );
        productCategory2 = new ProductCategoryModel(
                store,
                "Chifa"
        );
        product = new ProductModel(
                productCategory,
                "Pollo a la Brasa",
                "Pollo rico",
                20.5f,
                100
        );
    }

    @Test
    void getAllProducts() {
        Mockito.when(productRepository.findAllByOrderByIdAsc()).thenReturn(Arrays.asList(product));
        final List<ProductModel> result = productService.getAllProducts();
        assertEquals(result.get(0).getName(), product.getName());
    }

    @Test
    void getAllProductByCategory() {
        Mockito.when(productCategoryRepository.findById(ArgumentMatchers.anyInt())).thenReturn(productCategory);
        Mockito.when(productRepository.findAllByProductCategory(productCategory)).thenReturn(Arrays.asList(product));
        final List<ProductModel> result = productService.getAllProductByCategory(1);
        assertEquals(result.get(0),product);
    }

    @Test
    void getAllProductByStore() {
        Mockito.when(productCategoryService.getAllByStore(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(productCategory));
        Mockito.when(productRepository.findAllByProductCategory(productCategory)).thenReturn(Arrays.asList(product));
        Mockito.when(productRepository.findAllByProductCategory(productCategory2)).thenReturn(Arrays.asList());
        final List<ProductModel> result = productService.getAllProductByStore(1);
        assertEquals(result.get(0).getName(),product.getName());
    }

    @Test
    void getProductById() {
        Mockito.when(productRepository.findById(ArgumentMatchers.anyInt())).thenReturn(product);
        final ProductModel result = productService.getProductById(1);
        assertEquals(result, product);
    }

    @Test
    void createProduct() {
        Mockito.when(productCategoryRepository.findById(ArgumentMatchers.anyInt())).thenReturn(productCategory);
        Mockito.when(productRepository.save(ArgumentMatchers.any(ProductModel.class))).thenReturn(product);
        final ProductModel result = productService.createProduct(
                "",
                "",
                1f,
                10,
                1);
        assertEquals(result, product);
    }

    @Test
    void updateProduct() {
        Mockito.when(productCategoryRepository.findById(ArgumentMatchers.anyInt())).thenReturn(productCategory);
        Mockito.when(productRepository.save(ArgumentMatchers.any(ProductModel.class))).thenReturn(product);
        final ProductModel result = productService.updateProduct(
                1,
                "",
                "",
                1f,
                10,
                1);
        assertEquals(result, product);
    }

    @Test
    void existsProduct() {
        Mockito.when(productRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        final boolean result = productService.existsProduct(1);
        assertTrue(result);
    }

    @Test
    void deleteProduct(){
        Mockito.when(productRepository.findById(ArgumentMatchers.anyInt())).thenReturn(product);
        productService.deleteProduct(1);
    }
}