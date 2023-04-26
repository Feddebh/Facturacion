package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * User interface for Product management.
 *
 * @author Federico Bohle
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="https://github.com/Feddebh/Facturacion/tree/test">Facturacion</a>
 */
@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  /**
   * Endpoint for adding a new product with the provided data.
   *
   * @param candidateProductDTO the {@link ProductDTO} object containing the candidate product data.
   * @return a {@link ResponseEntity} object containing the created product data and HTTP status
   *     code 201 (CREATED).
   * @throws edu.coderhouse.jpa.exceptions.BillingException if the candidate product data is not
   *     valid.
   * @apiNote The newly added product can be used for various purposes such as inventory management,
   *     sales reporting, or customer support.
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO candidateProductDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.addNewProduct(candidateProductDTO));
  }

  /**
   * Updates an existing product in the system with the provided product ID and new product data.
   *
   * @param productId The ID of the product to update.
   * @param updatedProductDTO The updated product data to use for the product update.
   * @return ResponseEntity<Product> Object containing the updated product and a status of 200 OK on
   *     success
   * @throws edu.coderhouse.jpa.exceptions.BillingException if the updated product data is not
   *     valid.
   * @apiNote This endpoint is useful when the product data needs to be updated due to changes in
   *     the product's attributes or pricing.
   */
  @PutMapping(value = "/{productId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Product> updateExistingProduct(
      @PathVariable Long productId, @RequestBody ProductDTO updatedProductDTO) {
    return ResponseEntity.ok(productService.updateExistingProduct(productId, updatedProductDTO));
  }

  /**
   * Retrieves a list of all products currently stored in the system.
   *
   * @return a {@link ResponseEntity} Object containing the list of all products and a status of 200
   *     OK on success
   * @apiNote This endpoint can be used to fetch a complete list of all products in the system. The
   *     list can be used for various purposes such as display, filtering, sorting, or statistical
   *     analysis. Useful for debugging and troubleshooting issues related to the product data in
   *     the system.
   */
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  /**
   * Retrieves a product by its unique identifier number.
   *
   * @param productId The ID of the product to update.
   * @return A ResponseEntity containing a ProductDTO object representing the retrieved product,
   * @throws edu.coderhouse.jpa.exceptions.BillingException if the product ID is not valid.
   * @apiNote This endpoint can be used to fetch details of a specific product by its ID.
   */
  @GetMapping(value = "/{productId}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
    return ResponseEntity.ok(
        this.productMapper.productToProductDto(productService.getProductById(productId)));
  }
}
