package com.abhinav.supplierfinance.repository;

import com.abhinav.supplierfinance.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
