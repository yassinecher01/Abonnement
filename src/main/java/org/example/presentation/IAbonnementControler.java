package org.example.presentation;

import org.example.metier.IMetier;

public interface IAbonnementControler {
    void afficherAbonnement(Long var)throws Exception;
    void setAbonnementMetier(IMetier abonnementMetier);
}
