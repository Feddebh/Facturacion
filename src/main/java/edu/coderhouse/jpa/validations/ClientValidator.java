package edu.coderhouse.jpa.validations;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.entities.Client;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
@Component
public class ClientValidator {
    public void validate(Client client) throws BillingException {

        if(client == null){
            throw new BillingException("EMPTY.RESOURCE");
        }
        this.validateDocNumber(client.getDocNumber());
        this.validateLastName(client.getLastName());
        this.validateName(client.getName());
    }

    public void validateDocNumber(String docNumber) throws BillingException{
        String docNumberOnlyNumbers = docNumber.replaceAll("\\D","");
        boolean docNumberEmpty = StringUtils.isBlank(docNumberOnlyNumbers);
        boolean docNumberValidSize = (docNumberOnlyNumbers.length() > 6 && docNumberOnlyNumbers.length() <= 11);
        if (docNumberEmpty || !docNumberValidSize){
            throw new BillingException("INVALID.PARAMETER","DOC NUMBER");
        }
    }

    public void validateName(String name) throws BillingException {
        boolean nameValid = this.validateString(name);
        if (!nameValid) {
            throw new BillingException("INVALID.PARAMETER","NAME");
        }
    }

    public void validateLastName(String lastName) throws BillingException {
        boolean nameValid = this.validateString(lastName);
        if (!nameValid) {
            throw new BillingException("INVALID.PARAMETER","LASTNAME");
        }
    }


        private boolean validateString(String cadena){
            String cadenaSinCaracteresExtranios = cadena.replaceAll("[^a-zA-Z\\s]","");
            boolean cadenaNoVacia = StringUtils.isNotBlank(cadenaSinCaracteresExtranios);
            boolean cadenaConLogitudValida = (cadenaSinCaracteresExtranios.length() >= 3);

            return (cadenaNoVacia || cadenaConLogitudValida);
        }
    }

