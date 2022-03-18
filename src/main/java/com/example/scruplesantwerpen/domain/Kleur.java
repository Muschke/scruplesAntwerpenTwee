package com.example.scruplesantwerpen.domain;

import javax.persistence.*;


@Entity
@Table(name = "kleuren")
public class Kleur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idkleur;
    private String kleur;

    public Kleur(String kleur) {
        this.kleur = kleur;
    }

    protected Kleur() {};

    public String getKleur() {
        return kleur;
    }
}
