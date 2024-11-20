package com.example.demo.controller;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Commande> getCommandeById(@PathVariable Long id) {
        return commandeService.findById(id);
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        return commandeService.update(id, commandeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteById(id);
    }

    // Endpoint to add a LigneCommande to an existing Commande
    @PostMapping("/{commandeId}/ligneCommande")
    public LigneCommande addProductToCommande(
            @PathVariable Long commandeId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return commandeService.addProductToCommande(commandeId, productId, quantity);
    }

    // Endpoint to add a Commande to a specific Client
    @PostMapping("/client/{clientId}")
    public Commande addCommandeToClient(
            @PathVariable Long clientId,
            @RequestBody Commande commande
    ) {
        return commandeService.addCommandeToClient(clientId, commande);
    }
}
