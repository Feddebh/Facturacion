package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Client;
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
  public ResponseEntity<ProductDTO> addProduct (@Valid @RequestBody ProductDTO candidateProductDTO) throws BillingException {
    Product addedProduct = this.productService.addProduct(candidateProductDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
            .header("location", "/" + addedProduct.getId())
            .body(candidateProductDTO);
  } //OK


  @PutMapping(value = "/{productId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ProductDTO> updateProduct(
          @PathVariable Long productId, @Valid @RequestBody ProductDTO updatedproductDTO) throws BillingException {
    Product updatedProduct = this.productService.updateProduct(productId,updatedproductDTO);
    ProductDTO updatedProductResponse = this.productMapper.productToProductDto(updatedProduct);
    return ResponseEntity.ok(updatedProductResponse);
  } //OK

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Product>> getAllProducts() {

    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping(value = "/{productId}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProductById (@NotNull @PathVariable Long clientId) throws BillingException {
    Product product = this.productService.getProductById(clientId);
    return ResponseEntity.ok(this.productMapper.productToProductDto(product));
  }
}
