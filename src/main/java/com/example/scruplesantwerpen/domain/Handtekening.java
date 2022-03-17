package com.example.scruplesantwerpen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Handtekening {
    @Id @GeneratedValue private long idHandtekening;
    @Lob private byte[] content;
    private String name;

    /*getters*/

    public long getIdHandtekening() {
        return idHandtekening;
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
}
