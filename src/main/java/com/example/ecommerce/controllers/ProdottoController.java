package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.ProdottoCarrello;
import com.example.ecommerce.entities.Prodotto;
import com.example.ecommerce.services.ProdottoCarrelloService;
import com.example.ecommerce.services.ProdottoService;
import com.example.ecommerce.utility.MessaggioRisposta;
import com.example.ecommerce.utility.exception.JwtUtil;
import com.example.ecommerce.utility.exception.ProdottoEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prodotti")
@CrossOrigin("*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private ProdottoCarrelloService carrelloService;

    @PostMapping("/aggiungi")
    @PreAuthorize("hasAnyAuthority('admin')")
    public boolean aggiungi(@RequestBody @Valid Prodotto prodotto){
        try{
            Prodotto nuovoProdotto = prodottoService.aggiungiProdotto(prodotto);
            return true;
        }catch(ProdottoEsistenteException e){
            return false;
        }
    }

    @GetMapping()
    public ResponseEntity mostraTutti(){
        List<Prodotto> listaProdotti=prodottoService.mostraProdotti();
        return new ResponseEntity(listaProdotti, HttpStatus.OK);
    }

    @PostMapping("/aggiungiAlCarrello")
    @CrossOrigin(origins = "http://localhost:4200/**")
    @PreAuthorize("hasAuthority('cliente')")
    public Boolean aggiungiAlCarrello(@RequestBody Prodotto prodotto){
        ProdottoCarrello carrello = new ProdottoCarrello();
        try{
            carrello=carrelloService.aggiungiAlCarrello(prodotto);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @GetMapping("/prodottiCarrello")
    @CrossOrigin(origins = "http://localhost:4200/**")
    @PreAuthorize("hasAuthority('cliente')")
    public List<ProdottoCarrello> prodottiCarrello(){
        return carrelloService.prodottiCarrello(JwtUtil.getEmail());
    }

    @PostMapping("/rimuoviProdottoCarrello")
    @CrossOrigin(origins = "http://localhost:4200/**")
    @PreAuthorize("hasAuthority('cliente')")
    public void rimuoviProdottoCarrello(@RequestBody long id){
        carrelloService.rimuoviCarrello(id);
    }

    @GetMapping("/rimuoviProdottiCarrello")
    @CrossOrigin(origins = "http://localhost:4200/**")
    @PreAuthorize("hasAuthority('cliente')")
    public void rimuoviProdottiCarrello(){
        System.out.println("ciao");
        carrelloService.rimuoviCarrello();
    }

    @GetMapping("/pagine")
    public ResponseEntity mostraTutti(@RequestParam(value = "numeroPagine", defaultValue = "0")int numeroPagine, @RequestParam(value = "dimensionePagina", defaultValue = "10") int dimensionePagina, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy ){
        List<Prodotto> prodotti = prodottoService.mostraProdotti(numeroPagine, dimensionePagina, sortBy);
        if ( prodotti.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/ricerca/per_nome")
    public ResponseEntity ricercaPerNome(@RequestParam(required = false) String nome){
        List<Prodotto> prodotti=prodottoService.mostraProdottiPerNome(nome);
        if(prodotti.size()<=0){
            return new ResponseEntity<>(new MessaggioRisposta("NESSUN RISULTATO"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }
    @GetMapping("/getProdottiCategoria/{IDcategoria}")
    @CrossOrigin(origins = "http://localhost:4200/**")
    public List<Prodotto> getCategoria(@PathVariable ("IDcategoria") Long IDcategoria){
        return prodottoService.getProdottiCategoria(IDcategoria);
    }
}
