package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.Invoice;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.exception.InvalidInvoiceException;
import com.abhinav.supplierfinance.exception.InvoiceAlreadyExistsException;
import com.abhinav.supplierfinance.exception.InvoiceNotExistsException;
import com.abhinav.supplierfinance.service.ClientService;
import com.abhinav.supplierfinance.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    InvoiceService service;

    @PostMapping("/save")
    public ResponseEntity<Invoice> saveInvoice(@Valid @RequestBody Invoice invoice) throws InvoiceAlreadyExistsException, InvalidInvoiceException {
        return ResponseEntity.ok(service.saveInvoice(invoice));
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(service.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Invoice>> getInvoiceById(@PathVariable String id) {
        return ResponseEntity.ok(Collections.singletonList(service.getInvoiceById(id)));
    }

    @GetMapping("/supplierCode/{supplierCode}")
    public ResponseEntity<List<Invoice>> getAllInvoicesBySupplierCode(@PathVariable String supplierCode) {
        return ResponseEntity.ok(service.getAllInvoicesBySupplierCode(supplierCode));
    }

    @GetMapping("/clientUsername/{clientUsername}")
    public ResponseEntity<List<Invoice>> getAllInvoicesByClientUsername(@PathVariable String clientUsername) {
        return ResponseEntity.ok(service.getAllInvoicesByClientUsername(clientUsername));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Invoice> updateInvoiceStatus(@PathVariable String id, @RequestParam String status) throws InvoiceNotExistsException {
        return ResponseEntity.ok(service.updateInvoiceStatus(id, status));
    }
}
