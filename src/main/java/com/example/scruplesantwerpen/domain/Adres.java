package com.example.scruplesantwerpen.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Adres {
    private String straat;
    private String huisnummer;
    private String postcode;
    private String gemeente;

    public Adres(String straat, String huisnummer, String postcode, String gemeente) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    protected Adres() {};

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
