package com.example.ecommerce.services;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.repositories.CategoriaRepository;
import com.example.ecommerce.utility.exception.CategoriaNonEsistenteException;
import com.example.ecommerce.utility.exception.CategoriaEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listaCategorie(){
        return categoriaRepository.findAll();
    }

    public Categoria creaCategoria(Categoria categoria)throws CategoriaEsistenteException {
        categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria leggiCategoria(String categoria){
        return categoriaRepository.findByNome(categoria);
    }

    public Categoria aggiornaCategoria(Long categoriaID, Categoria nuovaCategoria)throws CategoriaNonEsistenteException {
        Categoria categoria= categoriaRepository.findById(categoriaID).get();
        if(categoria==null){
            throw new CategoriaNonEsistenteException();
        }
        categoria.setNome(nuovaCategoria.getNome());
        categoria.setDescrizione(nuovaCategoria.getDescrizione());
        categoria.setProdotti(nuovaCategoria.getProdotti());
        categoria.setUrlImmagine(nuovaCategoria.getUrlImmagine());
        
        categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria getCategoria(Long categoriaID){
        return categoriaRepository.findById(categoriaID).get();
    }



}
