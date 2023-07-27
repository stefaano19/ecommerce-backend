package com.example.ecommerce.services;

import com.example.ecommerce.entities.Prodotto;
import com.example.ecommerce.entities.ProdottoOrdinato;
import com.example.ecommerce.entities.Ordine;
import com.example.ecommerce.entities.Utente;
import com.example.ecommerce.repositories.*;
import com.example.ecommerce.utility.exception.JwtUtil;
import com.example.ecommerce.utility.exception.QuantitàNonDisponibileException;
import com.example.ecommerce.utility.exception.UtenteNonTrovatoException;
import com.example.ecommerce.utility.exception.VariazionePrezzoException;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ProdottoOrdinatoRepository prodottoOrdinatoRepository;

    @Autowired
    private ProdottoCarrelloRepository prodottoCarrelloRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {QuantitàNonDisponibileException.class, VariazionePrezzoException.class})
    public Ordine aggiungiOrdine(Ordine ordine) throws VariazionePrezzoException, QuantitàNonDisponibileException {
        String username = JwtUtil.getEmail();
        Utente utente= utenteRepository.findByUsername(username);
        Ordine nOrdine= new Ordine();
        nOrdine.setData(new Date(System.currentTimeMillis()));
        nOrdine.setUtente(utente);
        nOrdine.setProdottiOrdinati(ordine.getProdottiOrdinati());
        Ordine ordineS = ordineRepository.save(nOrdine);
        utenteRepository.save(utente);
        for(ProdottoOrdinato prodottoOrdinato : ordineS.getProdottiOrdinati()){
            prodottoOrdinato.setOrdine(ordineS);
            ProdottoOrdinato aggiunto=prodottoOrdinatoRepository.save(prodottoOrdinato);
            Prodotto prodotto= prodottoRepository.findById(prodottoOrdinato.getProdotto().getId()).get();
            if(prodotto.getPrezzo()>prodottoOrdinato.getPrezzo()){
                throw new VariazionePrezzoException();
            }
            int nuovaQuantità=prodotto.getQuantita()-prodottoOrdinato.getQuantita();
            if( nuovaQuantità<0){
                throw new QuantitàNonDisponibileException();
            }
            prodotto.setQuantita(nuovaQuantità);
        }
        prodottoCarrelloRepository.deleteAllByUtente(utente);
        return ordineS;
    }
/*

    @Transactional(readOnly = true)
    public List<Ordine> mostraOrdiniUtente(String username) throws UtenteNonTrovatoException {
        return ordineRepository.findAllByUsername(username);
    }

 */

    @Transactional(readOnly = true)
    public List<Ordine> mostraOrdini(){
        return ordineRepository.findAll();
    }
}
