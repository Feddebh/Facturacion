package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.ClientNotFoundException;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {

            throw new ClientNotFoundException("No se encuentra el producto con ID: " + productId);

        }
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




