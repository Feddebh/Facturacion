package edu.coderhouse.jpa.repository;

import edu.coderhouse.jpa.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.coderhouse.jpa.models.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


    Iterable<Invoice> findByClient(Client client);
}