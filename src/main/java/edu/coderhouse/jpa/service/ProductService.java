package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.NegativeStockException;
import edu.coderhouse.jpa.exceptions.ProductOutOfStockException;
import edu.coderhouse.jpa.models.entities.Product;
import java.util.List;

public interface ProductService {

  Product addProduct(Product candidateProduct);

  void deleteProduct(Long productId);

  List<Product> getAllProducts();

  Product getProductById(Long productId);

  Product updateProduct(Long productId, Product updatedProduct) throws NegativeStockException, ProductOutOfStockException;
}
