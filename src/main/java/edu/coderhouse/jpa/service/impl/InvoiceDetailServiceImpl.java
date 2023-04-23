package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.InvoiceDetailRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.InvoiceDetailService;
import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

  private final ProductRepository productRepository;

  private final InvoiceDetailRepository invoiceDetailRepository;

  public BigDecimal calculateDetailsTotal(InvoiceDetail detail, Invoice invoice, Integer amount) {
    Long productId = detail.getProduct().getId();

    Product product = productRepository.findById(productId).orElse(null);
    if (product == null) {
      return BigDecimal.ZERO;
    } else {
      // Configurar el detalle de factura con los datos necesarios
      detail.setInvoice(invoice);
      detail.setProduct(product);
      detail.setAmount(amount);
      detail.setAppliedPrice(product.getPrice());
    }
    // Calcular el total de la factura
    BigDecimal price = product.getPrice();
    amount = detail.getAmount();
    return price.multiply(new BigDecimal(amount));
  }

  @Override
  public InvoiceDetail saveInvoiceDetail(InvoiceDetail detail) {

    // Lógica de validación o procesamiento adicional antes de guardar el InvoiceDetail
    // ...

    // Guardar el InvoiceDetail en la base de datos
    return invoiceDetailRepository.save(detail);
  }
}
