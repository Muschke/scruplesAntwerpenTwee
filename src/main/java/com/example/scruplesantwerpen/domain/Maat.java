package com.example.scruplesantwerpen.domain;

import javax.persistence.*;

@Entity
@Table(name = "maten")
public class Maat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maatId;
    private String naam;

    public Maat(String naam) {
        this.naam = naam;
    }

    protected Maat() {};

    public String getNaam() {
        return naam;
    }
}
