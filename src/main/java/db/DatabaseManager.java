package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by thiba on 03/11/2016.
 */
public class DatabaseManager {
    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://defortet.ddns.net:3306/compteurweb", "vtfl", "vtfl");

            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery( "SELECT *  FROM locales;" );
            String pays="";
            int gmt=0;
            while ( resultat.next() ) {
                pays = resultat.getString( "Pays" );
                gmt = resultat.getInt( "GMT" );
            }
            System.out.println(pays +" : "+gmt);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
