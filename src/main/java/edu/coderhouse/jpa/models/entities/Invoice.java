package edu.coderhouse.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@JsonPropertyOrder({"id", "createdAt", "client", "invoiceDetails", "total"})
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("invoice_id")
  private Long id;

  @Column(name = "created_at", nullable = false)
  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @Column(name = "total")
  private BigDecimal total;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
  @JsonProperty("invoice_details")
  private List<InvoiceDetail> invoiceDetails;
}
