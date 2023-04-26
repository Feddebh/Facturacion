package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {

  Product addNewProduct(@Valid ProductDTO candidateProductDTO);

  List<Product> getAllProducts();

  Product getProductById(@Positive Long productId);

  Product updateExistingProduct(@Positive Long productId, @Valid ProductDTO updatedProductDTO);
}
