package com.kisaragi.app.storeCategory;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.store.StoreModel;

@Entity
@Table(name="StoreCategory")
public class StoreCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", length = 200)
	private String name;
	
	@ManyToMany(mappedBy = "storeCategories", cascade = CascadeType.DETACH)
	@JsonIgnore
	private Set<StoreModel> stores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StoreModel> getStores() {
		return stores;
	}

	public void setStores(Set<StoreModel> stores) {
		this.stores = stores;
	}
}