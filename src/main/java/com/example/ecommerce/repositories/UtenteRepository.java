package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Utente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import java.util.List;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    Utente findByUsername(String username);
    boolean existsByUsername(String username);
}
