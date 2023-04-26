package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO implements Serializable {

  @JsonProperty("name")
  @Size(min = 2, max = 50, message = "El campo 'nombre' admite entre 2 y 50 caracteres")
  @Pattern(regexp = "^[A-Za-z]+$", message = "Ha ingresado un caracter invalido ")
  private String name;

  @JsonProperty("last_name")
  @Size(min = 2, max = 50, message = "El campo 'apellido' admite entre 2 y 50 caracteres")
  @Pattern(regexp = "^[A-Za-z]+$")
  private String lastName;

  @JsonProperty("identification_number")
  @Size(min = 2, max = 11, message = "El DNI debe tener entre 2 y 11 caracteres.")
  @Pattern(regexp = "^[0-9]+$")
  private String docNumber;

  @JsonProperty("active")
  private boolean active = true;
}
