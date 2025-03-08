package org.example.onlinestore.controller;

import org.example.onlinestore.repo.ProductRepository;
import org.example.onlinestore.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository
                .findById(id)
                .map(new Function<Product, ResponseEntity<Product>>() {
                    @Override
                    public ResponseEntity<Product> apply(Product product) {
                        return ResponseEntity.ok(product);
                    }
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
