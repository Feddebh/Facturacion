package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseRequest {
  @JsonProperty("client_id")
  private String clientId;
  @JsonProperty("purchase_details")
  private List<ProductDTO> purchaseDetails;
}
//Esto me indica lo que le voy a pasar como cuerpo al postman para pedir la factura, el "Id" de cliente y una lista de
// product DTO que contiene el producto que pidió y la cantidad.
