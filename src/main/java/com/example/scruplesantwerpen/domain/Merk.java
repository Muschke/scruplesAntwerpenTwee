package com.example.scruplesantwerpen.domain;

import javax.persistence.*;

@Entity
@Table(name = "merken")
public class Merk {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idmerk;
    private String merk;

    public Merk(String naam) {
        this.merk = naam;
    }

    protected Merk() {};

    public String getNaam() {
        return merk;
    }
}
