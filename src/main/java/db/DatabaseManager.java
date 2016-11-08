package db;

import beans.Compteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
            String date="";
            Compteur c;
            while ( resultat.next() ) {
                id = resultat.getInt("id");
                titre = resultat.getString( "titre" );
                gmt = resultat.getInt( "gmt" );
                date = resultat.getString("date");
                c = new Compteur(id,titre,gmt,date,idSession);
                ret.add(c);
            }
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return ret;
    }

    public boolean ajouterCompteur(Compteur c){
        boolean myBool = false;
        try{
            Statement stmt = conn.createStatement();
            String titre=c.getTitre();
            int gmt=c.getGmt();
            String date=c.getDate();
            int idSession = c.getIdSession();
            //INSERT INTO `compteurs`(`titre`, `gmt`, `date`, `idSession`) VALUES ('testTitre',1,'2010/01/22',5)
            myBool = stmt.execute( "INSERT INTO `compteurs`(`titre`,`gmt`,`date`,`idSession`) VALUES ('"+titre+ "', '"+gmt+"' , '"+date+"' , '"+idSession+"');");
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return myBool;
    }

    public boolean supprimerCompteur(int idCompteur){
        boolean myBool = false;
        try{
            Statement stmt = conn.createStatement();
            myBool = stmt.execute( "DELETE FROM compteurs WHERE `id` = '"+idCompteur+"';");
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return myBool;
    }

    public boolean modifierCompteur(Compteur c){
        boolean myBool = false;
        try{
            Statement stmt = conn.createStatement();
            String titre=c.getTitre();
            int gmt=c.getGmt();
            String date=c.getDate();
            int idSession = c.getIdSession();
            myBool = stmt.execute( "UPDATE `compteurs` SET `titre` = '"+titre+"' ,`gmt` = "+gmt+",`date` = '"+date+"' WHERE id = "+c.getId()+" ;");

            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return myBool;
    }

    public boolean insererLocales(HashMap<String,Integer> hm){
        String insert = "INSERT INTO locales VALUES";
        String sql ="";
        for(String s : hm.keySet()){
            sql+=insert+" ('"+s+"' , "+hm.get(s)+");";
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
