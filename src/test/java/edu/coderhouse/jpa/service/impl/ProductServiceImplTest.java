package edu.coderhouse.jpa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import edu.coderhouse.jpa.mappers.ProductMapper;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

  @Mock private ProductRepository productRepository;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductServiceImpl productServiceImpl;

  @Test
  public void whenGetAllProducts_thenReturnList() {
    Product product1 = new Product();
    product1.setDescription("test");

    List<Product> mockedList = new ArrayList<>();
    mockedList.add(product1);

    when(productRepository.findAll()).thenReturn(mockedList);

    List<Product> resultado = productServiceImpl.getAllProducts();

    assertNotNull(resultado);
    assertEquals("test", resultado.get(0).getDescription());
  }

  @Test
  public void addNewProduct_whenCandidateProductDtoIsUnique_thenProductIsSavedSuccessfully() {
    // Arrange
    ProductDTO candidateProductDTO = new ProductDTO();
    candidateProductDTO.setCode("1234");
    candidateProductDTO.setDescription("Test product");
    candidateProductDTO.setPrice(new BigDecimal("10.00"));
    candidateProductDTO.setStock(10);

    Product product = new Product();
    product.setCode("1234");
    product.setDescription("Test product");
    product.setPrice(new BigDecimal("10.00"));
    product.setStock(10);

    when(productRepository.existsByCode(candidateProductDTO.getCode())).thenReturn(false);
    when(productMapper.productDtoToProduct(candidateProductDTO)).thenReturn(product);
    when(productRepository.save(product)).thenReturn(product);

    Product savedProduct = productServiceImpl.addNewProduct(candidateProductDTO);

    assertNotNull(savedProduct);
    assertEquals(product, savedProduct);
    verify(productRepository, times(1)).existsByCode(candidateProductDTO.getCode());
    verify(productMapper, times(1)).productDtoToProduct(candidateProductDTO);
    verify(productRepository, times(1)).save(product);
  }
}
