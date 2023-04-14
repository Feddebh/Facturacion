package edu.coderhouse.jpa.models.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", length = 150)
  @NotNull(message = "No se admite una descripcion del producto vacia.")
  @Size(min = 2, max = 150)
  @Pattern(regexp = "^[A-Za-z]+$")
  private String description;

  @Column(name = "code", length = 50)
  @NotNull(message = "No se admite un codigo de producto vacio.")
  @Pattern(regexp = "^[0-9]+$")
  private String code;

  @Column(name = "stock")
  @NotNull(message = "No se admite un stock de producto vacio")
  @Pattern(regexp = "^[0-9]+$")
  private Integer stock;

  @Column(name = "price")
  @NotNull(message = "No se admite un precio vacio.")
  @Pattern(regexp = "^[0-9]*\\.?[0-9]*$", message = "El precio ingresado no es válido")
  private BigDecimal price;
}
