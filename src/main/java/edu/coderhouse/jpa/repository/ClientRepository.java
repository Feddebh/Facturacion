package edu.coderhouse.jpa.repository;

import edu.coderhouse.jpa.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDocNumber(String docNumber);

}
