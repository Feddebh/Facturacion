package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import java.math.BigDecimal;
import edu.coderhouse.jpa.repository.InvoiceDetailRepository;
import edu.coderhouse.jpa.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    /*
    La clase tiene un método llamado calculateDetailsTotal que realiza el cálculo del total de detalles de una factura.
    El método toma tres parámetros como entrada: detail, que es un objeto de tipo InvoiceDetail que representa el detalle
     de la factura que se va a calcular; invoice, que es un objeto de tipo Invoice que representa la factura a la que
     pertenece el detalle; y amount, que es un entero que representa la cantidad de productos en el detalle de factura.
     */
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