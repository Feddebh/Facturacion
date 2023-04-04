package edu.coderhouse.jpa.models.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false, length = 75)
  private String name;
  @Column(name = "lastname", nullable = false, length = 75)
  private String lastName;
  @Column(name = "docnumber", nullable = false, length = 11)
  private String docNumber;
}
