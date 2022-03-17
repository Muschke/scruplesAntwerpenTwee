package com.example.scruplesantwerpen.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "producten")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @OneToOne @JoinColumn(name = "eigenschapId")
    private Eigenschap eigenschap;
    @OneToOne @JoinColumn(name = "kleurId")
    private Kleur kleur;
    @OneToOne @JoinColumn(name = "merkId")
    private Merk merk;
    @OneToOne @JoinColumn(name = "maatId")
    private Maat maat;
    @Enumerated(EnumType.STRING)
    private Soort soort;
    private Byte[] barcode; //zoals handtekening denk ik dat dat zal zijn
    private String beschrijving;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs; //akp.2
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean gestolen;
    private boolean solden;

    public Product(Eigenschap eigenschap, Kleur kleur, Merk merk, Maat maat, Soort soort, Byte[] barcode,
                   String beschrijving, BigDecimal aankoopprijs, Status status, boolean solden) {
        this.eigenschap = eigenschap;
        this.kleur = kleur;
        this.merk = merk;
        this.maat = maat;
        this.soort = soort;
        this.barcode = barcode;
        this.beschrijving = beschrijving;
        this.aankoopprijs = aankoopprijs;
        setVerkoopprijs(verkoopprijs);
        this.status = status;
        setGestolen(gestolen);
        this.solden = solden;
    }

    protected Product() {};

    public long getProductId() {
        return productId;
    }

    public Eigenschap getEigenschap() {
        return eigenschap;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public Merk getMerk() {
        return merk;
    }

    public Maat getMaat() {
        return maat;
    }

    public Soort getSoort() {
        return soort;
    }

    public Byte [] getBarcode() {
        return barcode;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isGestolen() {
        return gestolen;
    }

    public boolean isSolden() {
        return solden;
    }
    /*setters voor vkp en gestolen, en voor elke OTO*/

    public void setEigenschap(Eigenschap eigenschap) {
        this.eigenschap = eigenschap;
    }

    public void setKleur(Kleur kleur) {
        this.kleur = kleur;
    }

    public void setMerk(Merk merk) {
        this.merk = merk;
    }

    public void setMaat(Maat maat) {
        this.maat = maat;
    }


    public void setGestolen(boolean gestolen) {
        this.gestolen = false;
    }

    public void setVerkoopprijs(BigDecimal verkoopprijs) {
        this.verkoopprijs = aankoopprijs.multiply(BigDecimal.valueOf(2.5));
    }
    /*equals en hashtagcode maken*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(eigenschap, product.eigenschap) && Objects.equals(kleur, product.kleur) && Objects.equals(merk, product.merk) && Objects.equals(maat, product.maat) && soort == product.soort && Objects.equals(beschrijving, product.beschrijving) && Objects.equals(aankoopprijs, product.aankoopprijs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eigenschap, kleur, merk, maat, soort, beschrijving, aankoopprijs);
    }
}
