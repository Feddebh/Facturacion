package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired private ProductMapper productMapper;


  @Override
  public Product addProduct(@Valid ProductDTO candidateProductDTO) {
    log.info("NUEVO PRODUCTO: " + candidateProductDTO);
    Product newProduct = productMapper.productDtoToProduct(candidateProductDTO);
    return productRepository.save(newProduct);
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
  public Product getProductById(Long productId) throws BillingException{
    if (productId <= 0){
      throw new BillingException("EL ID INGRESADO NO ES VALIDO");
    }
    return this.productRepository.findById(productId).
            orElseThrow(() -> new BillingException("El ID especificado (aun) no existe."));
  }

  @Override
  public Product updateProduct(Long productId, ProductDTO updatedProductDTO)
          throws BillingException {

    Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new BillingException(
            "No se encuentra el producto con ID: " + productId));

      // Verificar si hay suficiente stock disponible
      if (existingProduct.getStock() < updatedProductDTO.getAmount()) {
        throw new BillingException("No hay suficiente stock disponible");
      }
      // Actualizar el producto
    existingProduct.setStock(updatedProductDTO.getAmount());
      return productRepository.save(existingProduct);
    }
}

