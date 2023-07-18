package com.example.ecommerce.services;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Prodotto;
import com.example.ecommerce.repositories.CategoriaRepository;
import com.example.ecommerce.utility.exception.ProdottoEsistenteException;
import com.example.ecommerce.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Prodotto> getProdotto(long id){
        return prodottoRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Prodotto aggiungiProdotto(Prodotto prodotto) throws ProdottoEsistenteException {
        if(prodottoRepository.existsByCodice(prodotto.getCodice())){
            throw new ProdottoEsistenteException();
        }
        long idC= Long.parseLong(prodotto.getIdC());
        Categoria categoria= categoriaRepository.findById(idC).get();
        prodotto.setCategoria(categoria);
        prodottoRepository.save(prodotto);
        return prodotto;
    }
    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdotti(){
        return prodottoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdotti(int numeroPagine, int dimensionePagine, String ordine){
        Pageable pagginazione = PageRequest.of(numeroPagine, dimensionePagine, Sort.by(ordine));
        Page<Prodotto> pagina = prodottoRepository.findAll(pagginazione);
        if(pagina.hasContent()){
            return pagina.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }
    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiPerNome(String nome) {
        return prodottoRepository.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiPerCodice(String codice) {
        return prodottoRepository.findByCodice(codice);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getProdottiCategoria(Long IdCategoria){
        return prodottoRepository.findByCategoria_Id(IdCategoria);
    }

}
