package com.abhinav.supplierfinance.service;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.ClientLogin;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Client registerClient(Client client) throws ClientAlreadyExistsException {
        if (getClientByUsername(client.getUsername()) != null) {
            throw new ClientAlreadyExistsException("Client is already registered!");
        }
        return obfuscatePassword(repository.save(client));
    }

    public Boolean loginClient(ClientLogin loginDetails) {
        Client client = repository.findById(loginDetails.getUsername()).orElse(null);
        if (client == null) return false;
        else return client.getPassword().equals(loginDetails.getPassword());
    }

    public List<Client> getAllClients() {
        return repository.findAll()
                .stream()
                .map(this::obfuscatePassword)
                .collect(Collectors.toList());
    }

    public Client getClientByUsername(String username) {
        return obfuscatePassword(repository.findById(username).orElse(null));
    }

    private Client obfuscatePassword(Client client) {
        if (client == null) return null;
        client.setPassword("*****");
        return client;
    }
}
