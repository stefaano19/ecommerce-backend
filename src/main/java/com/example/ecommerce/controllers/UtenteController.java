package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Utente;
import com.example.ecommerce.services.UtenteService;
import com.example.ecommerce.utility.MessaggioRisposta;
import com.example.ecommerce.utility.exception.JwtUtil;
import com.example.ecommerce.utility.exception.MailUserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    @PostMapping("/registrazione")
    @PreAuthorize("hasAnyAuthority('cliente')")
    public ResponseEntity create(@RequestBody @Valid Utente utente) {
        try {
            Utente added = utenteService.registerUser(utente);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (MailUserAlreadyExistsException e) {
            return new ResponseEntity<>(new MessaggioRisposta("ERROR_MAIL_USER_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Utente> getAll() {
        return utenteService.getUtenti();
    }

    @GetMapping("/getUtente")
    @PreAuthorize("hasAnyAuthority('cliente')")
    public Boolean getUtente(){
        return utenteService.getUtente(JwtUtil.getEmail());
    }
}
