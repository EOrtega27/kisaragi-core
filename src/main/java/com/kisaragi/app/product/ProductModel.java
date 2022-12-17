package com.kisaragi.app.product;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.OrderProduct.OrderProduct;
import com.kisaragi.app.productCategory.ProductCategoryModel;

import java.util.Set;

@Entity
@Table(name = "product")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
    @JoinColumn(name="productCategoryId", referencedColumnName = "id")
	private ProductCategoryModel productCategory;

	@OneToMany(mappedBy = "productId")
	@JsonIgnore
	private Set<OrderProduct> orderProducts;

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

	public ProductModel() {
	}

	public ProductModel(ProductCategoryModel productCategory, String name, String description, float unitPrice, int stock) {
		this.productCategory = productCategory;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.stock = stock;
	}

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

	public void setImage(String image) {
		this.image = image;
	}

	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
