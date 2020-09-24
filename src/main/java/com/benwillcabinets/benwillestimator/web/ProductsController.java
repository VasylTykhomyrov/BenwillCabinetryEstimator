package com.benwillcabinets.benwillestimator.web;

import com.benwillcabinets.benwillestimator.domain.Category;
import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import com.benwillcabinets.benwillestimator.refacing.materials.Colour;
import com.benwillcabinets.benwillestimator.refacing.materials.Handles;
import com.benwillcabinets.benwillestimator.refacing.materials.Style;
import com.benwillcabinets.benwillestimator.service.ProductService;
import com.benwillcabinets.benwillestimator.service.RefacingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin("*")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RefacingItemService refacingItemService;

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PutMapping("/products/{id}")
    ResponseEntity<Integer> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product){
        Product productToUpdate = productService.findById(id).get();
        productToUpdate.setCostPrice(product.getCostPrice());
        productToUpdate.setSellPrice(product.getSellPrice());
        productToUpdate.setQty(product.getQty());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setName(product.getName());
        productToUpdate.setUOM(product.getUOM());
        productService.save(productToUpdate);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/productsOfCategory")
    List<Product> getAllProductsFromCategory(@RequestParam String filterByCategory) {
        Product productOfCategory = new Product();
        productOfCategory.setCategory(Category.valueOf(filterByCategory));
        return productService.findAll(Example.of(productOfCategory));
    }

    @GetMapping("/categories")
    List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }

    @GetMapping("/colour")
    List<Colour> getAllColours() {
        return Arrays.asList(Colour.values());
    }

    @GetMapping("/style")
    List<Style> getAllStyles() {
        return Arrays.asList(Style.values());
    }

    @GetMapping("/handles")
    List<Handles> getAllHandles() {
        return Arrays.asList(Handles.values());
    }


    @PostMapping("/products")
    Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }


}
