package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.service.ProductService;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

  @Autowired private ProductService productService;
  @Autowired private ProductMapper productMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) throws BillingException {
    Product product = this.productMapper.productDtoToProduct(productDTO);
    Product addedProduct = this.productService.addProduct(product);
    return ResponseEntity.created(URI.create("/" + addedProduct.getId()))
            .body(this.productMapper.productToProductDto(addedProduct));
  }

  @PutMapping(value = "/{productId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ProductDTO> updateProduct(
      @PathVariable Long productId, @Valid @RequestBody ProductDTO productDTO) throws BillingException {
    Product product = this.productMapper.productDtoToProduct(productDTO);
    return new ResponseEntity<>(this.productMapper.productToProductDto(this.productService.updateProduct(product,productId)), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.ok().build();
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Product>> getAllProducts() {

    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping(value = "/{productId}", produces = "application/json")
  public ResponseEntity<Product> getProductById(@NotNull @PathVariable Long productId) {
    Product product = productService.getProductById(productId);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }
}
