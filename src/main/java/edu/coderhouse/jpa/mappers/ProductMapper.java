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
            // Si los nombres de los campos en la entidad y el DTO son iguales, no es necesario hacer la asignación explícita
            // @Mapping(source = "description", target = "description"),
            // @Mapping(source = "code", target = "code"),
            // @Mapping(source = "stock", target = "stock"),
            // @Mapping(source = "price", target = "price")
    })
    Product productDtoToProduct(ProductDTO productDTO);

    @Mappings({
            @Mapping(source = "id", target = "productId"),
            // Si los nombres de los campos en la entidad y el DTO son iguales, no es necesario hacer la asignación explícita
            // @Mapping(source = "description", target = "description"),
            // @Mapping(source = "code", target = "code"),
            // @Mapping(source = "stock", target = "stock"),
            // @Mapping(source = "price", target = "price")
    })
    ProductDTO productToProductDto(Product product);
}
