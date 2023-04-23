package edu.coderhouse.jpa.mappers;

import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "productId", target = "id")
    @Mapping(source = "amount", target = "stock")
    Product productDtoToProduct(ProductDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "stock", target = "amount")
    ProductDTO productToProductDto(Product product);
}
