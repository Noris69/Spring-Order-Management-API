package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.service.ClientService;
import com.example.demo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.update(id, clientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    // Endpoint to add a new Commande to a specific Client
    @PostMapping("/{clientId}/commandes")
    public Commande addCommandeToClient(@PathVariable Long clientId, @RequestBody Commande commande) {
        return commandeService.addCommandeToClient(clientId, commande);
    }
}
