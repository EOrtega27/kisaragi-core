package com.kisaragi.app.store;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kisaragi.app.dto.StoreDTO;
import com.kisaragi.app.productCategory.ProductCategoryModel;
import com.kisaragi.app.storeCategory.StoreCategoryModel;

@Entity
@Table(name="Store")
public class StoreModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "storeProductCategory")
	@JsonIgnore
	private Set<ProductCategoryModel> productCategories;
	
	@Column(name="adminId")
	private int adminId;
	
	@Column(name="name", length = 200)
	private String name;
	
	@Column(name="address", length = 200)
	private String address;
	
	@Column(name="latitude", precision = 14, scale = 7)
	private float latitude;
	
	@Column(name="longitude", precision = 15, scale = 7)
	private float longitude;

	@Column(name="telephone", length = 13)
	private String telephone;
	
	@Column(name="image")
	private String image;
	
	@Column(name="email", length = 35)
	private String email;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(
			name = "StoreCategoryStore", 
			joinColumns = { @JoinColumn(name = "StoreId") }, 
			inverseJoinColumns = @JoinColumn(name = "StoreCategoryId")
	)
    private Set<StoreCategoryModel> storeCategories;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<ProductCategoryModel> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategoryModel> productCategories) {
		this.productCategories = productCategories;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<StoreCategoryModel> getStoreCategories() {
		return storeCategories;
	}

	public void setStoreCategories(Set<StoreCategoryModel> storeCategories) {
		this.storeCategories = storeCategories;
	}

	
	public StoreModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreModel(StoreDTO data) {
		super();
		this.address = data.getAddress();
		this.adminId = data.getAdminId();
		this.email = data.getEmail();
		this.latitude = data.getLatitude();
		this.longitude = data.getLongitude();
		this.name = data.getName();
		this.telephone = data.getTelephone();
	}

	
}
