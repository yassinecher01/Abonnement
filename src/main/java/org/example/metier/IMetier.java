package org.example.metier;

import org.example.model.Abonnement;

public interface IMetier {
    Abonnement calculerAbonnemet(Long id) throws Exception;
}
