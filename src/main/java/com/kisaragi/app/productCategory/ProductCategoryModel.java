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
	
	@OneToMany(mappedBy = "productCategory")
	@JsonIgnore
	private Set<ProductModel> productList;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="storeId", referencedColumnName = "id")
    private StoreModel storeProductCategory;
	
	@Column(name="name")
	private String name;

	public ProductCategoryModel() {

	}

	public ProductCategoryModel(StoreModel storeProductCategory, String name) {
		this.storeProductCategory = storeProductCategory;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreProductCategory() {
		return storeProductCategory.getName();
	}

	public void setStoreProductCategory(StoreModel store_productCategory) {
		this.storeProductCategory = store_productCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductModel> getProductList() {
		return productList;
	}

	public void setProductList(Set<ProductModel> productList) {
		this.productList = productList;
	}
	
	
}
