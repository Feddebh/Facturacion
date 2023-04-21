package edu.coderhouse.jpa.validations;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ProductValidator {
    @Autowired
    private ProductRepository productRepository;

    public void validate(Product product) throws BillingException {
        if (product == null){
                throw new BillingException("EL PRODUCTO NO PUEDE ESTAR VACIO");
        }
        this.validateDescription(product.getDescription());
        this.validateCode(product.getCode());
        this.validateStock(product.getStock());
        this.validatePrice(product.getPrice());
    }
    public void validatePrice(BigDecimal price) throws BillingException {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BillingException("INVALID.PARAMETER", "UNIT PRICE");
        }
    }
    public void validateDescription(String description) throws BillingException {
        if (StringUtils.isBlank(description) || description.length() < 10){
            throw new BillingException("INVALID.PARAMETER","DESCRIPTION");
        }
    }

    public void validateStock(int stock) throws BillingException {
        if (stock <= 0){
            throw new BillingException("INVALID.PARAMETER","STOCK");
        }
    }
    public void validateCode(String code) throws BillingException{

        String codeOnlyNumbers = code.replaceAll("\\D","");
        boolean codeEmpty = StringUtils.isBlank(codeOnlyNumbers);
        if (codeEmpty){
            throw new BillingException("INVALID.PARAMETER","CODE");
        }

        Optional<Product> productWithSameCode = productRepository.findByCode(code);
        if (productWithSameCode.isPresent()) {
            throw new BillingException("RESOURCE.ALREADY.EXISTS", "CODE");
        }
    }

}
