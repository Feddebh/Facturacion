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
  private List<ProductDTO> products;
}
//Esto me indica lo que le voy a pasar como cuerpo al postman para pedir la factura, el id de cliente y una lista de
// product dto que contiene el producto que pidio y la cantidad.
