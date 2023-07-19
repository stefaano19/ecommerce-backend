package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@EqualsAndHashCode
@Entity
@Table(name="prodotti")
public class Prodotto {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Setter
    @Getter
    @Basic
    @Column(name="codice", nullable = false)
    private String codice;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name="id_categoria", nullable = false)
    Categoria categoria;

    @Setter
    @Getter
    @Basic
    @Column(name="nome", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Basic
    @Column(name="immagine", nullable = true)
    private String UrlImmagine;

    @Basic
    @Setter
    @Getter
    @Column(name="prezzo", nullable = false)
    private double prezzo;

    @Basic
    @Setter
    @Getter
    @Column(name="descrizione", nullable = true)
    private String descrizione;

    @Basic
    @Setter
    @Getter
    @Column(name="quantità", nullable = false)
    private int quantita;
    @Basic
    @Setter
    @Getter
    private String idC;

    @Version
    @Column(name="version", nullable = false)
    private long version;



}
