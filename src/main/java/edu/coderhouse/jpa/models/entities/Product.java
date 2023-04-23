package edu.coderhouse.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
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
  @Size(min = 2, max = 150, message = "El formato ingresado es incompatible o nulo")
  private String description;

  @Column(unique = true, name = "code", length = 50)
  @Size(min = 2, max = 50, message = "El formato ingresado es incompatible o nulo.")
  @Pattern(regexp = "^[0-9]+$")
  private String code;

  @Column(name = "stock")
  @NotNull(message = "No se admite un stock de producto vacio")
  @Positive
  @JsonIgnore
  private Integer stock;

  @Column(name = "price")
  @NotNull(message = "No se admite un precio vacio.")
  @Positive
  private BigDecimal price;
}
