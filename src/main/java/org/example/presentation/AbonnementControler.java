package org.example.presentation;

import org.example.metier.IMetier;
import org.example.model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AbonnementControler implements IAbonnementControler{
    @Autowired
    @Qualifier("metier")
    IMetier abonnementMetier;
    public void setAbonnementMetier(IMetier abonnementMetier){
        this.abonnementMetier=abonnementMetier;
    }
    @Override
    public void afficherAbonnement(Long var) throws Exception {
        Abonnement abnm=this.abonnementMetier.calculerAbonnemet(var);
        System.out.print(abnm);
    }
    public static boolean estunNombre(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }
}
