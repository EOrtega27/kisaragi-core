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
	private ProductCategoryModel productCategory;
	
	@Column(name="name", length = 50)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="unit_price", precision = 8, scale = 3)
	private float unitPrice;
	
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

	public ProductCategoryModel getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryModel product_productCategory) {
		this.productCategory = product_productCategory;
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

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unit_price) {
		this.unitPrice = unit_price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void getImage(String image) {
		this.image = image;
	}
	
	
}
