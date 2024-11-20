package com.example.demo.repository;

import com.example.demo.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    // Additional custom queries for LigneCommande can go here if needed
}
