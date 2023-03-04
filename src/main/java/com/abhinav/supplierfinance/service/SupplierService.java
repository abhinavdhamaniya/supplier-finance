package com.abhinav.supplierfinance.service;

import com.abhinav.supplierfinance.entity.Supplier;
import com.abhinav.supplierfinance.entity.SupplierLogin;
import com.abhinav.supplierfinance.exception.SupplierAlreadyExistsException;
import com.abhinav.supplierfinance.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository repository;

    public Supplier registerSupplier(Supplier supplier) throws SupplierAlreadyExistsException {
        if (getSupplierByCode(supplier.getSupplierCode()) != null) {
            throw new SupplierAlreadyExistsException("Supplier is already registered!");
        }
        return repository.save(supplier);
    }

    public Boolean loginSupplier(SupplierLogin loginDetails) {
        Supplier supplier = repository.findById(loginDetails.getSupplierCode()).orElse(null);
        if (supplier == null) return false;
        else return supplier.getPassword().equals(loginDetails.getPassword());
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public Supplier getSupplierByCode(String code) {
        return repository.findById(code).orElse(null);
    }
}
