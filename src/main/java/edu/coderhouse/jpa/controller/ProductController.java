package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Predicate;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> addProduct(@RequestBody Product candidateProduct){
        return ResponseEntity.ok(service.addProduct(candidateProduct));

}
}
