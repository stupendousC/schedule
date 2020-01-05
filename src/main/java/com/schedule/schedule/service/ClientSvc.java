package com.schedule.schedule.service;

import com.schedule.schedule.dao.ClientRepository;
import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ClientSvc {


    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllActives() {
        Iterable<Client> allClient = clientRepository.findAll();
        List<Client> allClientList = (List<Client>) allClient;

        List<Client> allActives = allClientList.stream()
                .filter(Client::getActive)
                .collect(Collectors.toList());

        return allActives;
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
            user.setActive(newInfoClient.getActive());
            clientRepository.save(user);
        });

        return clientMaybe;
    }

    public void deleteClient(long id) {
        Optional<Client> departingPerson = clientRepository.findById(id);
        departingPerson.ifPresent( person -> {
            person.setActive(false);
            clientRepository.save(person);
        });
    }

}

