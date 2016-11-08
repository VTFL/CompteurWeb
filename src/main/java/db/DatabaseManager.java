package db;

import beans.Compteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * Created by thiba on 03/11/2016.
 */
public class DatabaseManager {
    private  Connection conn;
    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection
                    ("jdbc:mysql://defortet.ddns.net:3306/compteurweb", "vtfl", "vtfl");
        } catch (Exception e) {
            e.printStackTrace();

        }
        //conn.close();
    }

    public ArrayList<Compteur> getCompteurs(int idSession){
        ArrayList<Compteur> ret = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery( "SELECT id,titre,gmt,date  FROM compteurs WHERE idSession="+idSession+";" );
            int id=0;
            String titre="";
            int gmt=0;
            Date date=null;
            Compteur c;
            while ( resultat.next() ) {
                id = resultat.getInt("id");
                titre = resultat.getString( "titre" );
                gmt = resultat.getInt( "gmt" );
                date = resultat.getDate("date");
                c = new Compteur(id,titre,gmt,date,idSession);
                ret.add(c);
            }
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return ret;
    }

    public void ajouterCompteur(Compteur c){
        try{
            Statement stmt = conn.createStatement();
            String titre=c.getTitre();
            int gmt=c.getGmt();
            Date date=c.getDate();
            int idSession = c.getIdSession();
            //INSERT INTO `compteurs`(`titre`, `gmt`, `date`, `idSession`) VALUES ('testTitre',1,'2010/01/22',5)
            ResultSet resultat = stmt.executeQuery( "INSERT INTO `compteurs`(`titre`,`gmt`,`date`,`idSession`) VALUES ('"+titre+ "', '"+gmt+"' , '"+date+"' , '"+idSession+"');");

            stmt.close();
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        ArrayList<Compteur> ac = db.getCompteurs(5);
        for(Compteur c : ac) System.out.println(c);
    }
}
