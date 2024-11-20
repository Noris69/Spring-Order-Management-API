package com.example.demo.service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.entity.Product;
import com.example.demo.entity.Client;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.LigneCommandeRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande update(Long id, Commande commandeDetails) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        commande.setDateCommande(commandeDetails.getDateCommande());
        commande.setEtat(commandeDetails.getEtat());
        return commandeRepository.save(commande);
    }

    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }

    // Method to add a product to an existing commande (creating a LigneCommande)
    public LigneCommande addProductToCommande(Long commandeId, Long productId, int quantity) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create a new LigneCommande and link it to the commande and product
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setCommande(commande);
        ligneCommande.setProduct(product);
        ligneCommande.setQuantite(quantity);

        // Save the LigneCommande
        return ligneCommandeRepository.save(ligneCommande);
    }

    // Method to add a new Commande to a Client
    public Commande addCommandeToClient(Long clientId, Commande commande) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        commande.setClient(client); // Set the client for the commande
        return commandeRepository.save(commande); // Save the new commande
    }
}
