package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseRequest {
  @JsonProperty("client_id")
  private Long clientId;

  @JsonProperty("purchase_details")
  private List<ProductAmountDTO> purchaseDetails;
}
