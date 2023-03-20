package org.example.metier;

import org.example.dao.IDao;
import org.example.model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("metier")
public class AbonnementMetier implements IMetier{
    @Autowired
    @Qualifier("dao")
IDao<Abonnement,Long>dao;
public void setAbonnementDao(IDao dao){
    this.dao=dao;
}

    public Abonnement calculerAbonnemet(Long id) throws Exception {
        Abonnement abnm = dao.trouverParId(id);
        if(abnm == null){
            System.out.print("abonnement introuvable");

        }else{
            switch(abnm.getDuree()){
                case 1 : {abnm.setPrix(300.0);break;}
                case 2 : {abnm.setPrix(500.0);break;}
                case 3 : {abnm.setPrix(700.0);break;}
                case 6 : {abnm.setPrix(900.0);break;}
                case 12 : {abnm.setPrix(1200.0);break;}
            }
        }
        return abnm;
    }
}
