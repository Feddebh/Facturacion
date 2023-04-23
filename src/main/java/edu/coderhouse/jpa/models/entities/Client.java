package edu.coderhouse.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "clients")
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 75)
  @Size(min = 2, max = 50, message = "El campo 'nombre' admite entre 2 y 50 caracteres")
  @Pattern(regexp = "^[A-Za-z]+$", message = "Ha ingresado un caracter invalido ")
  private String name;

  @Column(name = "lastname", length = 75)
  @Size(min = 2, max = 50, message = "El campo 'apellido' admite entre 2 y 50 caracteres")
  @Pattern(regexp = "^[A-Za-z]+$")
  private String lastName;

  @Column(unique = true, name = "docNumber", length = 11)
  @Size(min = 2, max = 11, message = "El DNI debe tener entre 2 y 11 caracteres.")
  @Pattern(regexp = "^[0-9]+$")
  private String docNumber;
}
