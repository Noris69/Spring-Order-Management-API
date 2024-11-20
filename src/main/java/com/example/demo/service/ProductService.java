package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.LigneCommande;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    // Méthode pour ajouter une LigneCommande (ligne de commande) à un produit existant
    public Product addLigneCommandeToProduct(Long productId, LigneCommande ligneCommande) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        // Associer le produit à la LigneCommande
        ligneCommande.setProduct(product);

        // Enregistrer la LigneCommande dans la base de données
        ligneCommandeRepository.save(ligneCommande);

        // Ajouter la LigneCommande à la liste de LigneCommandes du produit
        product.getLigneCommandes().add(ligneCommande);

        // Enregistrer et retourner le produit mis à jour
        return productRepository.save(product);
    }
}
