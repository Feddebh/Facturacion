package edu.coderhouse.jpa.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceDetailRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class BillingServiceImplTest {

  @Mock private InvoiceRepository invoiceRepository;
  @Mock private ClientRepository clientRepository;
  @Mock private InvoiceDetailRepository invoiceDetailRepository;
  @Mock private ProductRepository productRepository;
  @InjectMocks private BillingServiceImpl billingService;

  @Test
  public void givenInvoiceId_whenGetInvoiceById_thenReturnInvoice() {
    // SIMULAR / MOCKEAR RETORNOS (GIVEN)
    Invoice mockedInvoice = new Invoice();
    mockedInvoice.setTotal(new BigDecimal("100"));
    when(invoiceRepository.findById(1L)).thenReturn(Optional.of(mockedInvoice));

    // EJECUTAR ACCION  (WHEN)
    Invoice resultado = billingService.getInvoiceById(1L);

    // COMPROBAR RESULTADO (THEN)
    assertNotNull(resultado);
    assertEquals(resultado.getTotal(), new BigDecimal("100"));
  }

  @Test
  public void givenIdNotInDatabase_whenGetInvoice_thenThrowBillingException() {
    // MOCKEAR
    when(invoiceRepository.findById(2L)).thenReturn(Optional.empty());

    // COMPROBAR
    BillingException exception =
        assertThrows(BillingException.class, () -> billingService.getInvoiceById(2L));

    assertEquals("No se encontró la factura con el ID especificado. 2", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
  }
}
