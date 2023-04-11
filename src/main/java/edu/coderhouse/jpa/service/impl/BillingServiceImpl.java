package edu.coderhouse.jpa.service.impl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;

@Service
public class BillingServiceImpl {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Invoice> createInvoice(Invoice invoice) {

        // Obtener el cliente de la base de datos por su id
        Long clientId = invoice.getClient().getId();
        Client client = clientRepository.findById(clientId).orElse(null);

        // Verificar si el cliente existe
        if (client == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        // Configurar la fecha de creación de la factura
        invoice.setCreatedAt(LocalDateTime.now());

        // Calcular el total de la factura
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
            Long productId = detail.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }
            BigDecimal price = product.getPrice();
            Integer amount = detail.getAmount();
            BigDecimal detailTotal = price.multiply(new BigDecimal(amount));
            detail.setPrice(price);
            total = total.add(detailTotal);
        }
        invoice.setTotal(total);

        // Guardar la factura en la base de datos
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedInvoice);
    }

    public Iterable<Invoice> getInvoicesByClientId(Long clientId) {

            // Obtener el cliente de la base de datos por su id
            Client client = clientRepository.findById(clientId).orElse(null);

            // Verificar si el cliente existe
            if (client == null) {
                // Si el cliente no existe, se puede retornar una respuesta con un estado HTTP indicando que no se encontró el cliente
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Obtener las facturas del cliente
            Iterable<Invoice> invoices = invoiceRepository.findByClient(client);

            // Retornar las facturas del cliente en el cuerpo de la respuesta
            return invoices;
        }

    }
}