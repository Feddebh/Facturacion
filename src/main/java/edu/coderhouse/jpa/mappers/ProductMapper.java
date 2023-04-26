package edu.coderhouse.jpa.mappers;

import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

  @Mappings({
    @Mapping(source = "productId", target = "id"),
  })
  Product productDtoToProduct(ProductDTO productDTO);

  @Mappings({
    @Mapping(source = "id", target = "productId"),
  })
  ProductDTO productToProductDto(Product product);
}
