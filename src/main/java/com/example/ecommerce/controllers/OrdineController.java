package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Ordine;
import com.example.ecommerce.services.OrdineService;
import com.example.ecommerce.utility.MessaggioRisposta;
import com.example.ecommerce.utility.exception.QuantitàNonDisponibileException;
import com.example.ecommerce.utility.exception.UtenteNonTrovatoException;
import com.example.ecommerce.utility.exception.VariazionePrezzoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @PostMapping("/creazione")
    @PreAuthorize("hasAnyAuthority('cliente')")
    public Boolean create(@RequestBody @Valid Ordine ordine){
        try{
            Ordine nuovoOrdine=ordineService.aggiungiOrdine(ordine);
            return true;
        }catch(QuantitàNonDisponibileException e){
            return false;
        }catch (VariazionePrezzoException e){
            return false;
        }
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public List<Ordine> mostraOrdini(){
        List<Ordine> listaOrdini= ordineService.mostraOrdini();
        return listaOrdini;
    }
/*
    @GetMapping("/{utente}")
    @PreAuthorize("hasAnyAuthority('utente')")
    public ResponseEntity mostraOrdini(@Valid @PathVariable("utente") String utente){
        try{
            List<Ordine> listaOrdini= ordineService.mostraOrdiniUtente(utente);
            return new ResponseEntity<>(listaOrdini, HttpStatus.OK);
        }catch(UtenteNonTrovatoException e){
            return new ResponseEntity<>(new MessaggioRisposta("Utente Sconosciuto"), HttpStatus.BAD_REQUEST);
        }
    }

 */

}
