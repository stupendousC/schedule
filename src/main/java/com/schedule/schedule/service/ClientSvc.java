package com.schedule.schedule.service;

import com.schedule.schedule.dao.ClientRepository;
import com.schedule.schedule.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ClientSvc {


    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client addNewClient(Client client) {
        return clientRepository.save(client);
    }



    public Optional<Client> getClientById(long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> updateClient(long id, Client newInfoClient) {
        Optional<Client> clientMaybe = clientRepository.findById(id);

        clientMaybe.ifPresent( user -> {
            user.setAddress(newInfoClient.getAddress());
            user.setEmail(newInfoClient.getEmail());
            user.setName(newInfoClient.getName());
            user.setPhone(newInfoClient.getPhone());
            clientRepository.save(user);
        });

        return clientMaybe;
    }

    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

}

