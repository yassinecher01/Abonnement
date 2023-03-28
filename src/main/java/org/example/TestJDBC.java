package org.example;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydb",
                username="root",
                password="admin";

        Connection connection                   = null;
        Statement statement                     = null;
        ResultSet resultSet                     = null;
        ResultSetMetaData resultSetMetaData     = null;

        try {


            System.out.print("chargement reussi");
            connection = (Connection) DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.print("connexion etablie");
            } else {
                System.out.print("echec de connexion");
            }
            statement =connection.createStatement();
            resultSet=statement.executeQuery(" select * from abonnement ");
            resultSetMetaData = resultSet.getMetaData();

            System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------ \n");
            while(resultSet.next()){
                for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    System.out.print("\t "+resultSetMetaData.getColumnName(i).toUpperCase()
                            +" : "+resultSet.getObject(i).toString()+"\t |");
                }
            }
            System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------ \n");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
