package edu.coderhouse.jpa.models.entities;


import lombok.*;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
  private String description;

  @Column(name = "code", length = 50)
  private String code;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "price")
  private BigDecimal price;
}
