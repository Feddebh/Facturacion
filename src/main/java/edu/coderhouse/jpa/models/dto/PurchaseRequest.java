package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class PurchaseRequest {
    @JsonProperty("client_id")
private String clientId;
    private List<ProductDTO> products;
}
