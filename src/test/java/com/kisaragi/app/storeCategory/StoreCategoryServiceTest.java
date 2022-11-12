package com.kisaragi.app.storeCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StoreCategoryServiceTest {

    @Mock
    StoreCategoryRepository stCatRepository;

    @InjectMocks
    StoreCategoryService stCatService;

    private StoreCategoryModel stCat;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        stCat = new StoreCategoryModel();
        stCat.setName("Categoria Prueba");
    }

    @Test
    void findCategory() {
    }

    @Test
    void findAll() {
    }

    @Test
    void saveCategory() {
        Mockito.when(stCatRepository.save(ArgumentMatchers.any(StoreCategoryModel.class))).thenReturn(stCat);
        assertNotNull(stCatService.saveCategory(stCat));
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void existCategory() {
    }
}