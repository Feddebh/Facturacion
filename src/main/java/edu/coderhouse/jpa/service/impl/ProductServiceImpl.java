package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
private ProductRepository productRepository;


    @Override
    public Product addProduct(Product candidateProduct) {
        return productRepository.save(candidateProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {

        Product existingProduct = productRepository.findById(productId).orElse(null);
        if(existingProduct != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCode(updatedProduct.getCode());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setPrice(updatedProduct.getPrice());

            return productRepository.save(existingProduct);
        }
        return null;
    }

    }



