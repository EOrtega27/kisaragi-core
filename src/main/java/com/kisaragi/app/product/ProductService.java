package com.kisaragi.app.product;

import com.kisaragi.app.cloudinary.cloudinaryService;
import com.kisaragi.app.productCategory.ProductCategoryModel;
import com.kisaragi.app.productCategory.ProductCategoryRepository;
import com.kisaragi.app.productCategory.ProductCategoryService;
import com.kisaragi.app.store.StoreModel;
import com.kisaragi.app.store.StoreService;
import com.kisaragi.app.storeCategory.StoreCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private StoreService storeService;
    @Autowired
    cloudinaryService cloudinary;

    public List<ProductModel> getAllProducts(){
        return productRepository.findAllByOrderByIdAsc();
    }

    public List<ProductModel> getAllProductByCategory(int categoryId){
        ProductCategoryModel category = productCategoryRepository.findById(categoryId);
        return productRepository.findAllByProductCategory(category);
    }

    public List<ProductModel> getAllProductByStore(int storeId){
        List<ProductCategoryModel> categories = productCategoryService.getAllByStore(storeId);
        List<ProductModel> products = new ArrayList<>();
        ProductCategoryModel category;
        int size = categories.size();
        for (int i=0; i<size; i++){
            category = categories.get(i);
            products.addAll(productRepository.findAllByProductCategory(category));
        }
        return products;
    }

    public ProductModel getProductById(int productId){
        return productRepository.findById(productId);
    }

    public ProductModel createProduct(String name, String description, float unitPrice, int stock, int categoryId){
        ProductCategoryModel category = productCategoryRepository.findById(categoryId);
        ProductModel product = new ProductModel(category, name, description, unitPrice, stock);
        return productRepository.save(product);
    }

    public ProductModel updateProduct(int productId, String name, String description, float unitPrice, int stock, int categoryId){
        ProductCategoryModel category = productCategoryRepository.findById(categoryId);
        ProductModel product = new ProductModel(category, name, description, unitPrice, stock);
        product.setId(productId);
        return productRepository.save(product);
    }

    public ProductModel uploadProductImage(MultipartFile multipartFile, int productId) throws IOException{
        Map result = cloudinary.upload(multipartFile);
        ProductModel product = getProductById(productId);
        product.setImage(result.get("url").toString());
        return productRepository.save(product);
    }

    public void deleteProduct(int productId){
        ProductModel product = getProductById(productId);
        productRepository.delete(product);
    }
}
