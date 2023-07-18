package com.example.ecommerce.services;

import com.example.ecommerce.entities.ProdottoCarrello;
import com.example.ecommerce.entities.Prodotto;
import com.example.ecommerce.entities.Utente;
import com.example.ecommerce.repositories.ProdottoCarrelloRepository;
import com.example.ecommerce.repositories.ProdottoRepository;
import com.example.ecommerce.repositories.UtenteRepository;
import com.example.ecommerce.utility.exception.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProdottoCarrelloService {
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ProdottoCarrelloRepository carrelloRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false)
    public ProdottoCarrello aggiungiAlCarrello(Prodotto prodotto) throws  Exception{
        ProdottoCarrello carrello = new ProdottoCarrello();
        try{
            String username = JwtUtil.getEmail();
            Utente utente= utenteRepository.findByUsername(username);
            Prodotto p = prodottoRepository.findById(prodotto.getId()).get();
            carrello.setUtente(utente);
            carrello.setQuantita(1);
            carrello.setPrezzo(p.getPrezzo());
            carrello.setProdottoId(p);
            carrello.setNomeProdotto(p.getNome());
            Date date = new Date();
            carrello.setData(date);
            carrelloRepository.save(carrello);
        }catch(Exception e){
            throw new Exception();
        }
            return carrello;
    }



    @Transactional(readOnly = true)
    public List<ProdottoCarrello> prodottiCarrello(String username){
        return carrelloRepository.findAllByUtente_Username(username);
    }
    @Transactional(readOnly = false)
    public void rimuoviCarrello(long id){
        carrelloRepository.deleteCarrellosById(id);
    }

}
