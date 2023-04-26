package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillingRequest implements Serializable {
  @JsonProperty("client_id")
  @NotNull
  @Positive
  @Valid
  private Long clientId;

  @JsonProperty("billing_details")
  private List<BillingDetail> billingDetails;
}
