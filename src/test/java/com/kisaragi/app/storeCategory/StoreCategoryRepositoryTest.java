package com.kisaragi.app.storeCategory;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreCategoryRepositoryTest {
    @Autowired
    StoreCategoryRepository stCatRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveStoreCategory(){
        StoreCategoryModel stCat = new StoreCategoryModel();
        stCat.setName("Librería");
        StoreCategoryModel stCatGuardado = stCatRepository.save(stCat);
        assertNotNull(stCatGuardado);
    }

    @Test
    public void FindById() {
        StoreCategoryModel sc01 = new StoreCategoryModel("Librería");
        StoreCategoryModel sc02 = new StoreCategoryModel("Bodega");
        entityManager.persist(sc01);
        entityManager.persist(sc02);
        entityManager.flush();

        StoreCategoryModel found = stCatRepository.findById(1);

        assertEquals(found.getName(),sc01.getName());
        assertNotEquals(found.getName(),sc02.getName());

    }
}