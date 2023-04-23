package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class ProductAmountDTO implements Serializable {

    @JsonProperty("product_id")
    private Long productId;


    private Integer amount;
}
