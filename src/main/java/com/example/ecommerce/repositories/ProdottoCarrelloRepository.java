package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProdottoCarrello;
import com.example.ecommerce.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoCarrelloRepository extends JpaRepository<ProdottoCarrello, Long> {
    void deleteCarrellosById(long carrelloId);
    List<ProdottoCarrello> findAllByUtente_Username(String username);

    void deleteAllByUtente(Utente utente);

}
