package edu.coderhouse.jpa.controller;


import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.service.ProductService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product candidateProduct){
        return ResponseEntity.ok(productService.addProduct(candidateProduct));
    }

    @PutMapping(value= "/{productId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId, @Valid @RequestBody Product updatedProduct){
        Product updated = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping(value= "/{productId}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
            productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> getAllProducts(){

        return ResponseEntity.ok(productService.getAllProducts());

    }
    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<Product> getProductById(@NotNull @PathVariable Long productId){
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


}
