package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailServiceImpl {

    @Autowired private ProductRepository productRepository;

    public BigDecimal calculateDetailTotal(InvoiceDetail detail) {
        Long productId = detail.getProduct().getId();
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal price = product.getPrice();
        Integer amount = detail.getAmount();
        return price.multiply(new BigDecimal(amount));
    }

}
