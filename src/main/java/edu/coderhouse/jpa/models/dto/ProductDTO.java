package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

  @JsonProperty("product_id")
  private Long productId;
  private Integer amount;
}
