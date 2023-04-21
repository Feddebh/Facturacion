package edu.coderhouse.jpa.exceptions;

import lombok.Data;

@Data
public class BillingException extends Exception{

    private String field;

    public BillingException(String msg){
        super(msg);
    }

    public BillingException(String msg, String field){
        super(msg);
        this.field = field;
    }
}