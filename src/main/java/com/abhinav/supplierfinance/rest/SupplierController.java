package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.ClientLogin;
import com.abhinav.supplierfinance.entity.Supplier;
import com.abhinav.supplierfinance.entity.SupplierLogin;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.exception.SupplierAlreadyExistsException;
import com.abhinav.supplierfinance.service.ClientService;
import com.abhinav.supplierfinance.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    @Autowired
    SupplierService service;

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerSupplier(@Valid @RequestBody Supplier supplier) throws SupplierAlreadyExistsException {
        return ResponseEntity.ok(service.registerSupplier(supplier));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody SupplierLogin loginDetails) {
        return ResponseEntity.ok(service.loginSupplier(loginDetails));
    }

    @GetMapping()
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(service.getAllSuppliers());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Supplier> getSupplierByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.getSupplierByCode(code));
    }
}
