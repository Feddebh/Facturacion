package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import java.util.List;

public interface ProductService {

  Product addProduct(ProductDTO candidateProductDTO) throws BillingException;

  void deleteProduct(Long productId);

  List<Product> getAllProducts();

  Product getProductById(Long productId) throws BillingException;

  Product updateProduct(Long productId, ProductDTO updatedProduct) throws BillingException;
}
