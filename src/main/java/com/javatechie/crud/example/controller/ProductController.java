package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service; //useless
    @Autowired
    private ProductRepository repository;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return repository.saveAll(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
    	Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }
}
