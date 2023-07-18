package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    /*
    List<Ordine> findAllByUsername(String username);
     */
}
