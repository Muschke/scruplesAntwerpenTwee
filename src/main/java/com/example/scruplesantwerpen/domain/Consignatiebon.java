package com.example.scruplesantwerpen.domain;

import org.springframework.context.annotation.Import;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "consignatieBonnen")
public class Consignatiebon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConsignatiebon;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "gebruikerId")
    private Gebruiker gebruiker;
    private LocalDate datumIn;
    private byte[] handtekening;
    @OneToMany //@JoinColumn(name = "productId")
    //@OrderBy(atr1, atr2, ..)
    private Set<Product> productSet = new LinkedHashSet<>();

    public Consignatiebon(Gebruiker gebruiker, LocalDate datumIn, byte[] handtekening) {
        this.gebruiker = gebruiker;
        this.datumIn = datumIn;
        this.handtekening = handtekening;
    }

    protected Consignatiebon() {};

    /*alle getters*/

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public long getidConsignatiebon() {
        return idConsignatiebon;
    }

    public LocalDate getDatumIn() {
        return datumIn;
    }

    public LocalDate getDatumUit() {
        return datumIn.plusMonths(6);
    }

    public byte[] getHandtekening() {
        return handtekening;
    }

    public boolean getIsGetekend() {
        if(handtekening == null) {
            return false;
        } else {
            return true;
        }
    }

    /*getter en setter voor set*/

    public Set<Product> getProductSet() {
        return Collections.unmodifiableSet(productSet);
    }

    public boolean add(Product product) {
        if(product == null) {
            throw new NullPointerException();
        }
        return productSet.add(product);
    }

    /*equals en hashcode voorzien*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consignatiebon)) return false;
        Consignatiebon that = (Consignatiebon) o;
        return Objects.equals(datumIn, that.datumIn) && Objects.equals(productSet, that.productSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumIn, productSet);
    }
}
