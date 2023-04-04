package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
private ProductRepository productRepository;


    @Override
    public Product addProduct(Product candidateProduct) {
        return productRepository.save(candidateProduct);
    }
}
