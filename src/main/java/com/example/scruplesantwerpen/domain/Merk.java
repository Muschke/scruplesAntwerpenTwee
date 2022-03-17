package com.example.scruplesantwerpen.domain;

import javax.persistence.*;

@Entity
@Table(name = "merken")
public class Merk {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long merkId;
    private String naam;

    public Merk(String naam) {
        this.naam = naam;
    }

    protected Merk() {};

    public String getNaam() {
        return naam;
    }
}
