package org.example.dao;

import org.example.model.Abonnement;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Component("dao1")

public class AbonnementDao implements IDao <Abonnement,Long>{

    public Set<Abonnement> BD_Abonnement(){
        return new HashSet(Arrays.asList(   new Abonnement(1L,"yassine",3,0.0),
                                            new Abonnement(2L,"anass",2, 0.0),
                                            new Abonnement(3L,"mehdi",6, 0.0)));
    }
    public Abonnement trouverParId(Long id) {
        return this.BD_Abonnement().stream().filter((Abonnement)->{
            return Abonnement.getId()==id;
        }).findFirst().orElse(null);
    }
}

