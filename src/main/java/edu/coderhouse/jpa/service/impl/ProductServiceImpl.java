package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.ProductNotFoundException;
import edu.coderhouse.jpa.exceptions.ProductOutOfStockException;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepository productRepository;

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

      throw new ProductNotFoundException("No se encuentra el producto con ID: " + productId);
    }
  }

  @Override
  public Product updateProduct(Long productId, Product updatedProduct) {

    Product existingProduct = productRepository.findById(productId).orElse(null);
    if (existingProduct != null) {
      // Verificar si hay suficiente stock
      int updatedStock = updatedProduct.getStock();
      if (updatedStock < 0) {
        throw new ProductOutOfStockException("El stock no puede ser negativo");
      }

      // Verificar si hay suficiente stock disponible
      if (existingProduct.getStock() < updatedStock) {
        throw new ProductOutOfStockException("No hay suficiente stock disponible");
      }
      // Esto estaria mal planteado hasta el momento ya que yo lo que busco es que el stock se
      // actualice al
      // hacer una compra en el caso de que haya stock suficiente, por lo que creo que las
      // validaciones deberian
      // ser contempladas en el service de invoice o invoicedetails. Preguntar a facu/

      existingProduct.setDescription(updatedProduct.getDescription());
      existingProduct.setCode(updatedProduct.getCode());
      existingProduct.setStock(updatedProduct.getStock());
      existingProduct.setPrice(updatedProduct.getPrice());

      return productRepository.save(existingProduct);
    }
    throw new ProductNotFoundException("No se encuentra el producto con ID: " + productId);
  }
}
