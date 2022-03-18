package com.example.scruplesantwerpen.domain;

import javax.persistence.*;

@Entity
@Table(name = "maten")
public class Maat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maatId;
    private String maat;

    public Maat(String naam) {
        this.maat = maat;
    }

    protected Maat() {};

    public String getNaam() {
        return maat;
    }
}
