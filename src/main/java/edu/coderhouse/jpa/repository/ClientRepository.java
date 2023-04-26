package edu.coderhouse.jpa.repository;

import edu.coderhouse.jpa.models.entities.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  Optional<Client> findByDocNumber(String docNumber);

  Optional<Client> findClientByIdAndActiveTrue(Long id);
}
