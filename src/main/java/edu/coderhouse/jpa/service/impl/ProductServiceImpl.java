package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public Product addNewProduct(@Valid ProductDTO candidateProductDTO) {
    if (productRepository.existsByCode(candidateProductDTO.getCode())) {
      throw new BillingException("El código ya está en uso", HttpStatus.BAD_REQUEST);
    } else {
      log.info("NUEVO PRODUCTO: " + candidateProductDTO);
      Product newProduct = productMapper.productDtoToProduct(candidateProductDTO);
      Product savedProduct = productRepository.save(newProduct);
      savedProduct.setId(savedProduct.getId());
      return savedProduct;
    }
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long productId) {
    if (productId <= 0) {
      throw new BillingException("EL ID INGRESADO NO ES VALIDO", HttpStatus.BAD_REQUEST);
    }
    return this.productRepository
        .findById(productId)
        .orElseThrow(
            () -> new BillingException("No se encontro el producto", HttpStatus.NOT_FOUND));
  }

  @Override
  public Product updateExistingProduct(Long productId, ProductDTO updatedProductDTO) {

    Product existingProduct =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new BillingException(
                        "No se encuentra el producto con ID: " + productId, HttpStatus.NOT_FOUND));

    existingProduct.setDescription(updatedProductDTO.getDescription());
    existingProduct.setCode(updatedProductDTO.getCode());
    existingProduct.setStock(updatedProductDTO.getStock());
    existingProduct.setPrice(updatedProductDTO.getPrice());
    return productRepository.save(existingProduct);
  }
}
