package com.example.ecommerce.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Table(name="categorie")
@Data
public class Categoria {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="categoria", nullable = true, length = 200)
    private String descrizione;

    @Basic
    @Column(name="immagine", nullable = true)
    private String urlImmagine;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Prodotto> prodotti;


}
