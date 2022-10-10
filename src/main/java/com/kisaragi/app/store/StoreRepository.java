package com.kisaragi.app.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreModel, Integer>{
	public StoreModel findById(int id);

}
