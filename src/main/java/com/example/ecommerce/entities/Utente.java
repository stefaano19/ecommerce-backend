package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "utenti")
public class Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "nome", nullable = true, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = true, length = 50)
    private String cognome;

    @Basic
    @Column(name = "numeroTelefono", nullable = true, length = 20)
    private String numeroTelefono;

    @Basic
    @Column(name = "username", nullable = true, length = 90)
    private String username;

    @Basic
    @Column(name = "indirizzo", nullable = true, length = 150)
    private String indirizzo;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ordine> ordini;

}
