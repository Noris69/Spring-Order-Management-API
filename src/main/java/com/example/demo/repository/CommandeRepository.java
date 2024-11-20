package com.example.demo.repository;

import com.example.demo.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Additional custom query methods (if needed) can go here
}
