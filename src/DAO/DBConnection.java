package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3406/gestion_employee";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "root";
    static Connection conn = null;

    public static Connection getConnexion(){
        if(conn == null){
            try {
                conn = DriverManager.getConnection(URL,UTILISATEUR,MOT_DE_PASSE);
            } catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Error lors de la connexion avec DB");
            }
        }
        return conn;
    }
        //jointure entre conge and employee
}
