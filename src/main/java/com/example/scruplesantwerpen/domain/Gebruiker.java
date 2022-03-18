package com.example.scruplesantwerpen.domain;




import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "gebruikers")
public class Gebruiker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idgebruiker;
    private String voornaam;
    private String naam;
    private String mail;
    private boolean mailOk;
    private String gebruikersnaam;
    private String pass;
    private String telefoonNr;
    @Embedded
    private Adres adres;
    @Enumerated(EnumType.STRING)
    private Gebruikersrol rol;
    private String extraOpmerking;
    @OneToMany(mappedBy = "gebruiker") //@JoinColumn(name = "consignatiebonId")
    //@OrderBy(attribute1, attribute2,..
    private Set<Consignatiebon> consignatiebonSet = new LinkedHashSet<>();

    public Gebruiker(String voornaam, String naam, String mail, boolean mailOk, String gebruikersnaam, String pass,
                     String telefoonNr, Adres adres, Gebruikersrol rol, String extraOpmerking) {
        this.voornaam = voornaam;
        this.naam = naam;
        this.mail = mail;
        this.mailOk = mailOk;
        this.gebruikersnaam = gebruikersnaam;
        this.pass = pass;
        this.telefoonNr = telefoonNr;
        this.adres = adres;
        this.rol = rol;
        this.extraOpmerking = extraOpmerking;
    }

    protected Gebruiker() {};

    /*getters*/



    public long getIdgebruiker() {
        return idgebruiker;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getMail() {
        return mail;
    }

    public boolean isMailOk() {
        return mailOk;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPass() {
        return pass;
    }

    public String getTelefoonNr() {
        return telefoonNr;
    }

    public Adres getAdres() {
        return adres;
    }

    public Gebruikersrol getRol() {
        return rol;
    }

    public String getExtraOpmerking() {
        return extraOpmerking;
    }


    /*mogelijkheid om consignatiebonnen in te voegen*/
    public Set<Consignatiebon> getConsignatiebonSet() {
        return Collections.unmodifiableSet(consignatiebonSet);
    }

    public boolean add(Consignatiebon bon) {
        if (bon == null) {
            throw new NullPointerException();
        }
        return consignatiebonSet.add(bon);
    }

}
