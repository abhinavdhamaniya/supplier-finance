package com.abhinav.supplierfinance.service;

import com.abhinav.supplierfinance.entity.Invoice;
import com.abhinav.supplierfinance.exception.InvalidInvoiceException;
import com.abhinav.supplierfinance.exception.InvoiceAlreadyExistsException;
import com.abhinav.supplierfinance.exception.InvoiceNotExistsException;
import com.abhinav.supplierfinance.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository repository;

    @Autowired
    ClientService clientService;

    @Autowired
    SupplierService supplierService;

    public Invoice saveInvoice(Invoice invoice) throws InvoiceAlreadyExistsException, InvalidInvoiceException {
        invoice.setStatus("AWAITING");
        if (getInvoiceById(invoice.getId()) != null) {
            throw new InvoiceAlreadyExistsException("Invoice is already created with id!" + invoice.getId());
        } else if (clientService.getClientByUsername(invoice.getClientUsername()) == null) {
            throw new InvalidInvoiceException("Invalid client username");
        } else if (supplierService.getSupplierByCode(invoice.getSupplierCode()) == null) {
            throw new InvalidInvoiceException("Invalid supplier code");
        }
        return repository.save(invoice);
    }

    public Invoice uploadInvoiceFile(String invoiceId, MultipartFile invoiceFile) throws InvoiceNotExistsException, IOException {
        Invoice invoice = getInvoiceById(invoiceId);
        if (getInvoiceById(invoice.getId()) == null) {
            throw new InvoiceNotExistsException("Invoice with id: " + invoice.getId() + " does not exist!");
        }

        byte[] fileContent = invoiceFile.getBytes();
        invoice.setInvoiceFileContent(fileContent);
        return repository.save(invoice);
    }

    public Invoice getInvoiceById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public List<Invoice> getAllInvoicesBySupplierCode(String supplierCode) {
        return repository.findAll()
                .stream()
                .filter(invoice -> invoice.getSupplierCode().equals(supplierCode))
                .collect(Collectors.toList());
    }

    public List<Invoice> getAllInvoicesByClientUsername(String clientUsername) {
        return repository.findAll()
                .stream()
                .filter(invoice -> invoice.getClientUsername().equals(clientUsername))
                .collect(Collectors.toList());
    }

    public Invoice updateInvoiceStatus(String id, String status) throws InvoiceNotExistsException {
        Invoice invoice = getInvoiceById(id);
        if (invoice == null) {
            throw new InvoiceNotExistsException("Invoice with id: " + id + " does not exist!");
        }
        invoice.setStatus(status);
        return repository.save(invoice);
    }
}
