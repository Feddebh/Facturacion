package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class ProductDTO implements Serializable {

  @JsonProperty("product_id")
  private Long productId;

  @Positive(message = "El Stock No se puede ser negativo")
  private Integer amount;
}
