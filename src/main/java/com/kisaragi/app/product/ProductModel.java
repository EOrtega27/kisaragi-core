package com.kisaragi.app.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kisaragi.app.productCategory.ProductCategoryModel;

@Entity
@Table(name = "product")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="productCategoryId", referencedColumnName = "id")
	private ProductCategoryModel product_productCategory;
	
	@Column(name="name", length = 50)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="unit_price", precision = 8, scale = 3)
	private float unit_price;
	
	@Column()
	private int stock;
	
	@Column(name="image")
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductCategoryModel getProduct_productCategory() {
		return product_productCategory;
	}

	public void setProduct_productCategory(ProductCategoryModel product_productCategory) {
		this.product_productCategory = product_productCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getIamge() {
		return image;
	}

	public void setIamge(String iamge) {
		this.image = iamge;
	}
	
	
}
