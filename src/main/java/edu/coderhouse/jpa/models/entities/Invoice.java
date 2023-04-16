package edu.coderhouse.jpa.models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "total")
  private BigDecimal total;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
  private List<InvoiceDetail> invoiceDetails;

}
