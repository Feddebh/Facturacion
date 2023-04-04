package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product candidateProduct);
    void deleteProduct(Long productId);

    List<Product> getAllProducts();
}
