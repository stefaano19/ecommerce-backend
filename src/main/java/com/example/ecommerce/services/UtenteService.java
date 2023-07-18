package com.example.ecommerce.services;

import com.example.ecommerce.entities.Utente;
import com.example.ecommerce.repositories.UtenteRepository;
import com.example.ecommerce.utility.exception.JwtUtil;
import com.example.ecommerce.utility.exception.MailUserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registerUser(Utente utente) throws MailUserAlreadyExistsException {
        if (utenteRepository.existsByUsername(JwtUtil.getEmail()) ){
            throw new MailUserAlreadyExistsException();
        }
        utente.setUsername(JwtUtil.getEmail());
        return utenteRepository.save(utente);
    }


    @Transactional(readOnly = true)
    public List<Utente> getUtenti() {
        return utenteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Boolean getUtente(String username) {
        return utenteRepository.existsByUsername(username);
    }

}