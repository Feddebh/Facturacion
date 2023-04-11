package edu.coderhouse.jpa.models.entities;

import lombok.Getter;
import lombok.Setter;

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


@Entity
@Table(name = "invoice_details")
@Getter
@Setter
public class InvoiceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invoice_detail_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "price", nullable = false)
  private BigDecimal price;
}
