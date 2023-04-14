package edu.coderhouse.jpa.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;

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
  @NotNull(message = "No se admite un nombre vacio.")
  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z]+$")
  private String name;

  @Column(name = "lastname", length = 75)
  @NotNull(message = "No se admite un apellido vacio.")
  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z]+$")
  private String lastName;

  @Column(name = "docNumber", length = 11)
  @NotNull(message = "No se admite un DNI vacio.")
  @Pattern(regexp = "^[0-9]+$")
  private String docNumber;
}
