package com.kisaragi.app.store;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreModel, Integer>{
	public StoreModel findById(int id);
	public List<StoreModel>  findAllByAdminId(String adminId);
	public List<StoreModel>  findAllByOrderByIdAsc();

}
