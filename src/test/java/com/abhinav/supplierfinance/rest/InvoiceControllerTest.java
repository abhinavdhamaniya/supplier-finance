package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Invoice;
import com.abhinav.supplierfinance.exception.InvalidInvoiceException;
import com.abhinav.supplierfinance.exception.InvoiceAlreadyExistsException;
import com.abhinav.supplierfinance.exception.InvoiceNotExistsException;
import com.abhinav.supplierfinance.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @Mock
    InvoiceService service = mock(InvoiceService.class);

    @InjectMocks
    InvoiceController controller = new InvoiceController();

    @Test
    void should_verify_status_as_ok_and_call_service_to_register_invoice() throws InvoiceAlreadyExistsException, InvalidInvoiceException {
        // Given
        Invoice invoice = new Invoice();
        invoice.setId("test");
        when(service.saveInvoice(invoice)).thenReturn(invoice);
        // When
        ResponseEntity<Invoice> result = controller.saveInvoice(invoice);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getId()).isEqualTo(invoice.getId());
    }


    @Test
    void should_verify_status_as_ok_and_call_service_to_get_all_invoices() {
        // Given
        List<Invoice> invoices = Collections.singletonList(new Invoice());
        when(service.getAllInvoices()).thenReturn(invoices);
        // When
        ResponseEntity<List<Invoice>> result = controller.getAllInvoices();
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_invoice_by_id() {
        // Given
        String id = "test";
        Invoice invoice = new Invoice();
        invoice.setId(id);
        when(service.getInvoiceById(id)).thenReturn(invoice);
        // When
        ResponseEntity<List<Invoice>> result = controller.getInvoiceById(id);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).get(0).getId()).isEqualTo(id);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_invoice_by_client_username() {
        // Given
        String username = "test";
        List<Invoice> invoices = Collections.singletonList(new Invoice());
        when(service.getAllInvoicesByClientUsername(username)).thenReturn(invoices);
        // When
        ResponseEntity<List<Invoice>> result = controller.getAllInvoicesByClientUsername(username);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_invoice_by_supplier_code() {
        // Given
        String supplierCode = "test";
        List<Invoice> invoices = Collections.singletonList(new Invoice());
        when(service.getAllInvoicesBySupplierCode(supplierCode)).thenReturn(invoices);
        // When
        ResponseEntity<List<Invoice>> result = controller.getAllInvoicesBySupplierCode(supplierCode);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_update_invoice_status() throws InvoiceNotExistsException {
        // Given
        String id = "test";
        String status = "APPROVED";
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setStatus(status);
        when(service.updateInvoiceStatus(id, status)).thenReturn(invoice);
        // When
        ResponseEntity<Invoice> result = controller.updateInvoiceStatus(id, status);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getId()).isEqualTo(id);
        assertThat(result.getBody().getStatus()).isEqualTo(status);
    }
}
