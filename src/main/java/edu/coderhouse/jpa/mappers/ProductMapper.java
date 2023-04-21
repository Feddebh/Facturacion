package edu.coderhouse.jpa.mappers;

import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

        Product productDtoToProduct(ProductDTO productDTO);

        ProductDTO productToProductDto(Product product);
    }