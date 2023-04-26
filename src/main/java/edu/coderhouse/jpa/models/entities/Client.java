package edu.coderhouse.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "clients")
@Data
@JsonPropertyOrder({"id", "name", "lastName", "docNumber", "active"})
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 75)
  private String name;

  @Column(name = "lastname", length = 75)
  @JsonProperty("last_name")
  private String lastName;

  @Column(unique = true, name = "docNumber", length = 11)
  @JsonProperty("identification_number")
  private String docNumber;

  @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
  private boolean active = true;
}
