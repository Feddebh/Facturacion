package edu.coderhouse.jpa.models.dto;
import lombok.Data;
import java.io.Serializable;
@Data
public class ErrorResponse implements Serializable {
    private int statusCode;
    private String status;
    private String message;

}
