package edu.coderhouse.jpa.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 75)
  @NotBlank(message = "No se admite un nombre vacio.")
  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z]+$")
  private String name;

  @Column(name = "lastname", length = 75)
  @NotBlank(message = "No se admite un apellido vacio.")
  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z]+$")
  private String lastName;

  @Column(unique = true, name = "docNumber", length = 11)
  @NotBlank(message = "No se admite un DNI vacio.")
  @Pattern(regexp = "^[0-9]+$")
  private String docNumber;
}
