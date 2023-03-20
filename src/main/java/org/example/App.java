package org.example;

import org.example.dao.AbonnementDao;
import org.example.dao.IDao;
import org.example.metier.AbonnementMetier;
import org.example.model.Abonnement;
import org.example.presentation.AbonnementControler;
import org.example.presentation.IAbonnementControler;
import org.example.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

import static org.example.presentation.AbonnementControler.estunNombre;

/**
 * Hello world!
 *
 */
public class App
{
    static Scanner clavier;
    static IAbonnementControler abonnementControler;
    public static void test1(){
        AbonnementDao dao = new AbonnementDao();
        AbonnementMetier metier = new AbonnementMetier();
        AbonnementControler controler= new AbonnementControler();
        metier.setAbonnementDao(dao);
        controler.setAbonnementMetier(metier);
        String rep = "";

        do {
            System.out.print("calculer l'abonnemment de l'adherant");

            try {
                String input = "";

                while(true) {
                    System.out.print("=> entrer l'id");
                    input = clavier.nextLine();
                    if (estunNombre(input)) {
                        long id_yassch = Long.parseLong(input);
                        controler.afficherAbonnement(id_yassch);
                        break;
                    }

                    System.out.print("entrer non valide");
                }
            } catch (Exception var7) {
                System.err.print(var7.getMessage());
            }

            System.out.print("voulez vous quitter?");
            rep = clavier.nextLine();
        } while(!rep.equals("oui"));

        System.out.print("byy");
    }
    public static void test2() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");
        if (propertiesFile == null) {
            throw new Exception("fichier config intouvable !!!");
        } else {
            String daoClass;
            String serviceClass;
            String controllerClass;
            try {
                properties.load(propertiesFile);
                daoClass = properties.getProperty("DAO");
                serviceClass = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            } catch (IOException var18) {
                throw new Exception("probleme de chargement des proprietes du fichier config");
            } finally {
                properties.clear();
            }

            try {
                Class cDao = Class.forName(daoClass);
                Class cMetier = Class.forName(serviceClass);
                Class cControleur = Class.forName(controllerClass);
                var dao  = (IDao<Abonnement,Long>)cDao.getDeclaredConstructor().newInstance();
                var metier =(IMetier) cMetier.getDeclaredConstructor().newInstance();
                abonnementControler = (IAbonnementControler) cControleur.getDeclaredConstructor().newInstance();

                Method setDao = cMetier.getMethod("setAbonnementDao",IDao.class);
                setDao.invoke(metier,dao);

                Method setMetier = cControleur.getMethod("setAbonnementMetier", IMetier.class);
                setMetier.invoke(abonnementControler,metier);

                abonnementControler.afficherAbonnement(1L);
            } catch (Exception var17) {
                var17.printStackTrace();
            }

        }
    }
    public static void test3() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        abonnementControler = (IAbonnementControler) context.getBean("controler");
        abonnementControler.afficherAbonnement(1L);

    }
    public static void test4() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext("dao","metier","presentation");
        abonnementControler = (IAbonnementControler) context.getBean(IAbonnementControler.class);
        abonnementControler.afficherAbonnement(1L);
}
    public static void main( String[] args ) throws Exception {

        test4();

    }
}
