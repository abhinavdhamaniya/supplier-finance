package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.ClientLogin;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    ClientService service;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@Valid @RequestBody Client client) throws ClientAlreadyExistsException {
        return ResponseEntity.ok(service.registerClient(client));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody ClientLogin loginDetails) {
        return ResponseEntity.ok(service.loginClient(loginDetails));
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Client> getClientByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.getClientByUsername(username));
    }
}
