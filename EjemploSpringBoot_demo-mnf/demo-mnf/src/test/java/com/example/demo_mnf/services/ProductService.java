package com.example.demo_mnf.services;

import com.example.demo_mnf.model.Product;
import com.example.demo_mnf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

    @Service
    public class ProductService {

        @Autowired
        private ProductService ProductRepository;

        public List<Product> listProducts() {
            return ProductRepository.findAll();
        }

        public Product searchProduct(Long id) {
            Product p = ProductRepository.findById(id).orElse(null);
            return p;
        }

        public Product saveProduct(Product p) {
            return ProductRepository.save(p);

        }

        public boolean deleteProduct(Product p) {
            if (ProductRepository.existsById(id)) {
                ProductRepository.deleteById(id);
                return true;
            }
            return false;
        }

        }

