package com.example.scruplesantwerpen.domain;

import javax.persistence.*;

@Entity
@Table(name = "eigenschappen")
public class Eigenschap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eigenschapId;
    private String clusterEigenschap;
    private String subEigenschap;

    public Eigenschap(String clusterEigenschap, String subEigenschap) {
        this.clusterEigenschap = clusterEigenschap;
        this.subEigenschap = subEigenschap;
    }

    protected Eigenschap() {};

    public long getEigenschapId() {
        return eigenschapId;
    }

    public String getClusterEigenschap() {
        return clusterEigenschap;
    }

    public String getSubEigenschap() {
        return subEigenschap;
    }
}
