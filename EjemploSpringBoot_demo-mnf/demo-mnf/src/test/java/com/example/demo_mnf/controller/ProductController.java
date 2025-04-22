package com.example.demo_mnf.controller;

import com.example.demo_mnf.model.Product;
import com.example.demo_mnf.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductController {
    @RestController
    @RequestMapping
    public class ProductController {
        @Autowired
        private ProductService productService;

        @GetMapping("/product")
        public List<Product> listProducts() {
            return productService.listProducts();
        }

        @GetMapping("/product/{id}")
        public ResponseEntity<Product> getProduct(@PathVariable long id)
        {
        Product p = Productservice.searchProduct(id);
        if (p == null) {
            return new ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
        }


        @PostMapping("/product")
        public ResponseEntity<Product> createProduct(@RequestBody Product p) {
            {
                Product create = productService.saveProduct(p);
                return new ResponseEntity<>(Product, HttpStatus.CREATED);
            }
        }

        @PutMapping("/product/{id}") //aqui
        public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
            try {
                Product currentProduct = productService.findById(id);
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                productService.save(currentProduct);
                return new ResponseEntity<>(currentProduct, HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/product/{id}")
        public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
            try {
                productService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
            }
        }


    }
}
