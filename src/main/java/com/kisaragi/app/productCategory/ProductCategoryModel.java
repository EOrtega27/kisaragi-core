package com.kisaragi.app.productCategory;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.product.ProductModel;
import com.kisaragi.app.store.StoreModel;

@Entity
@Table(name = "productCategory")
public class ProductCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "product_productCategory")
	@JsonIgnore
	private Set<ProductModel> product_list;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="storeId", referencedColumnName = "id")
    private StoreModel store_productCategory;
	
	@Column(name="name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStore_productCategory() {
		return store_productCategory.getName();
	}

	public void setStore_productCategory(StoreModel store_productCategory) {
		this.store_productCategory = store_productCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductModel> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(Set<ProductModel> product_list) {
		this.product_list = product_list;
	}
	
	
}
