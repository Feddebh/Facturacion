package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BillingDetail implements Serializable {
  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("amount")
  private Integer amount;
}
