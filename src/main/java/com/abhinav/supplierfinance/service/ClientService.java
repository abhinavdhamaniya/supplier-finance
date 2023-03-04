package com.abhinav.supplierfinance.service;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.ClientLogin;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Client registerClient(Client client) throws ClientAlreadyExistsException {
        if (getClientByUsername(client.getUsername()) != null) {
            throw new ClientAlreadyExistsException("Client is already registered!");
        }
        return repository.save(client);
    }

    public Boolean loginClient(ClientLogin loginDetails) {
        Client client = repository.findById(loginDetails.getUsername()).orElse(null);
        if (client == null) return false;
        else return client.getPassword().equals(loginDetails.getPassword());
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientByUsername(String username) {
        return repository.findById(username).orElse(null);
    }
}
