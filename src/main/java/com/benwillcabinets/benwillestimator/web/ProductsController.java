package com.benwillcabinets.benwillestimator.web;

import com.benwillcabinets.benwillestimator.domain.Category;
import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.refacing.materials.Colour;
import com.benwillcabinets.benwillestimator.refacing.materials.Handles;
import com.benwillcabinets.benwillestimator.refacing.materials.Style;
import com.benwillcabinets.benwillestimator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin("*")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productService.findAll();
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
