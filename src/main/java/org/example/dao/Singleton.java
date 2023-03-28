package org.example.dao;

import java.sql.*;

public class Singleton {
    private static Connection connection;

    private Singleton(){
        String  url          ="jdbc:mysql://localhost:3306/mydb",
                username    ="root",
                password    ="admin",
                driver      ="com.mysql.cj.jdbc.Driver";
        try{
            Class.forName(driver);
            connection= DriverManager.getConnection(url,username,password);
            System.out.print("creation de l'instance connexion reussite \n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        if(connection == null) new Singleton();
        else{
            System.out.print("Appel a l'instance de connexion de existante \n");
        }

        return connection;
    }

    public static void main(String[] args) {
        try{
            PreparedStatement ps = getConnection().prepareStatement("select * from abonnement");
            Statement state =getConnection().createStatement();
            getConnection().setAutoCommit(false);
            DatabaseMetaData meta = getConnection().getMetaData();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
