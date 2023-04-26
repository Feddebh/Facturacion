package edu.coderhouse.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice_details")
@Getter
@Setter
@JsonPropertyOrder({"id", "product", "amount", "appliedPrice", "subtotal"})
public class InvoiceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invoice_detail_id")
  @JsonIgnore
  private Long id;

  @ManyToOne
  @JoinColumn(name = "invoice_id")
  @JsonIgnore
  private Invoice invoice;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  // precio unitario * amount
  @Column(name = "subtotal", nullable = false)
  private BigDecimal subtotal;

  // precio unitario del producto al tiempo de efectuar la factura
  @Column(name = "applied_price", nullable = false)
  @JsonProperty("applied_price")
  private BigDecimal appliedPrice;
}
