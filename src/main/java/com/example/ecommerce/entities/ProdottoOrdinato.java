package com.example.ecommerce.entities;

import com.example.ecommerce.repositories.UtenteRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="ProdottiOrdinati")
@Data
public class ProdottoOrdinato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(nullable = false)
    private int quantita;

    @Basic
    @Column(name="prezzo", nullable = false)
    private double prezzo;

    @Basic
    private Date data;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ordine")
    private Ordine ordine;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="prodotto_id", referencedColumnName = "id")
    private Prodotto prodotto;

    @OneToOne
    private Utente utente;
}
