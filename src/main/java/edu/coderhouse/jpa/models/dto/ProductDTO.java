package edu.coderhouse.jpa.models.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

  private Long productId;

  @NotNull(message = "La descripción del producto no puede ser nula")
  @Size(min = 2, max = 150, message = "El formato ingresado es incompatible o nulo")
  private String description;

  @NotNull(message = "El código no puede ser nulo")
  @Size(min = 2, max = 50, message = "El formato ingresado es incompatible o nulo.")
  @Pattern(regexp = "^[0-9]+$", message = "El código solo debe contener números")
  private String code;

  @NotNull(message = "El stock no puede ser nulo")
  @Positive(message = "El stock debe ser un número positivo")
  private Integer stock;

  @NotNull(message = "El precio no puede ser nulo.")
  @Positive(message = "El precio debe ser un número positivo")
  private BigDecimal price;
}
