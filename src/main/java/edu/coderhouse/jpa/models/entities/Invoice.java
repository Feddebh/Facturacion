package edu.coderhouse.jpa.models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
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

  public InvoiceDetail[] getInvoiceDetails() {
    return new InvoiceDetail[0];
  }

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;


}
