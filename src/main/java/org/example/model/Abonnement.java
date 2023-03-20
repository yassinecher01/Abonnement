package org.example.model;

import lombok.Data;
@Data
public class Abonnement {

    private Long id;
    private String adherant;
    private Integer duree;
    private Double prix;

    public Abonnement(long id, String adherant, int duree, Double prix) {
        setId(id);
        setAdherant(adherant);
        setDuree(duree);
        setPrix(prix);
    }

    public String toString() {
        String  abonnement= "============================= \n" +
                            "abonnement n°:"+getId()+"\n"+
                            "adherant     :"+getAdherant()+"\n"+
                            "duree        :"+getDuree()+"\n"+
                            "prix         :"+getPrix()+"\n"+
                            "=============================";
        return abonnement;

    }
}
