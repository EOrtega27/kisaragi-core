package com.kisaragi.app.product;

import com.kisaragi.app.dto.IdRequest;
import com.kisaragi.app.dto.product.SaveProductRequest;
import com.kisaragi.app.dto.product.UpdateProductRequest;
import com.kisaragi.app.productCategory.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    //READ
    @GetMapping(" ")
    private ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/by_id")
    private ResponseEntity<Object> getProductById(IdRequest id){
        return new ResponseEntity<>(productService.getProductById(id.getId()),HttpStatus.OK);
    }
    @GetMapping("/by_category")
    private  ResponseEntity<Object> getProductByCategory(IdRequest id){
        return new ResponseEntity<>(productService.getAllProductByCategory(id.getId()),HttpStatus.OK);
    }
    @GetMapping("/by_store")
    private  ResponseEntity<Object> getProductByStore(IdRequest id){
        return new ResponseEntity<>(productService.getAllProductByStore(id.getId()),HttpStatus.OK);
    }

    //UPDATE
    @PostMapping("/update")
    private ResponseEntity<Object> updateProduct(@RequestBody UpdateProductRequest request){
        if(!productCategoryService.existCategory(request.getCategoryId())){
            return new ResponseEntity<>("Product Category Not Found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
          productService.updateProduct(
                  request.getId(),
                  request.getName(),
                  request.getDesciption(),
                  request.getUnitPrice(),
                  request.getStock(),
                  request.getCategoryId()
          ),
          HttpStatus.OK
        );
    }

    //CREATE
    @PostMapping("/save")
    private ResponseEntity<Object> saveProduct(@RequestBody SaveProductRequest request){
        if(!productCategoryService.existCategory(request.getCategoryId())){
            return new ResponseEntity<>("Product Category Not Found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                productService.createProduct(
                        request.getName(),
                        request.getDesciption(),
                        request.getUnitPrice(),
                        request.getStock(),
                        request.getCategoryId()
                ),
                HttpStatus.OK
        );
    }

    //DELETE
    @DeleteMapping("/delete")
    private ResponseEntity<Object> deleteProduct(@RequestBody IdRequest idRequest){
        if(productService.existsProduct(idRequest.getId())){
            productService.deleteProduct(idRequest.getId());
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
        }
    }

}
