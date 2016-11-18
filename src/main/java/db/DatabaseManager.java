package db;

import beans.Compteur;

import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

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
            ResultSet resultat = stmt.executeQuery( "SELECT id,titre,Pays,date,gmt  FROM compteurs JOIN locales USING(Pays) WHERE idSession="+idSession+";" );
            int id=0;
            String titre="";
            String pays="";
            String date="";
            int gmt=0;
            Compteur c;
            while ( resultat.next() ) {
                id = resultat.getInt("id");
                titre = resultat.getString( "titre" );
                pays = resultat.getString( "Pays" );
                date = resultat.getString("date");
                gmt=resultat.getInt("gmt");
                c = new Compteur(id,titre,pays,date,idSession);
                c.setGmt(gmt);
                ret.add(c);
            }
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return ret;
    }

    public Compteur getCompteur(int idCompteur){
        Compteur ret = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery( "SELECT titre,Pays,date,gmt,idSession FROM compteurs JOIN locales USING(Pays) WHERE id="+idCompteur+";" );
            String titre="";
            String pays="";
            String date="";
            int gmt=0;
            int idSession=0;
            resultat.next();
            titre = resultat.getString( "titre" );
            pays = resultat.getString( "Pays" );
            date = resultat.getString("date");
            gmt=resultat.getInt("gmt");
            ret = new Compteur(idCompteur,titre,pays,date,idSession);
            ret.setGmt(gmt);

            stmt.close();
        }catch(Exception e){e.printStackTrace();}

        return ret;
    }

    public boolean ajouterCompteur(Compteur c){
        boolean myBool = false;
        try{
            Statement stmt = conn.createStatement();
            String titre=c.getTitre();
            String pays=c.getPays();
            String date=c.getDate();
            int idSession = c.getIdSession();
            //INSERT INTO `compteurs`(`titre`, `gmt`, `date`, `idSession`) VALUES ('testTitre',1,'2010/01/22',5)
            myBool = stmt.execute( "INSERT INTO `compteurs`(`titre`,`Pays`,`date`,`idSession`) VALUES ('"+titre+ "', '"+pays+"' , '"+date+"' , '"+idSession+"');");
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
            String pays=c.getPays();
            String date=c.getDate();
            int idSession = c.getIdSession();
            myBool = stmt.execute( "UPDATE `compteurs` SET `titre` = '"+titre+"' ,`Pays` = '"+pays+"',`date` = '"+date+"' WHERE id = "+c.getId()+" ;");

            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return myBool;
    }

    public boolean insererLocales(){
        String insert = "INSERT INTO locales VALUES";
        String sql ="";
        HashMap<String,Integer> hm = parse();
        Boolean myBool;
        int cpt=0;
        for(String s : hm.keySet()){

            sql+=insert+" ('"+s.replace('\'',' ')+"' , "+hm.get(s)+"); ";
            try{
                Statement stmt = conn.createStatement();
                myBool = stmt.execute(sql);

                stmt.close();
            }catch(Exception e){e.printStackTrace();}
            sql="";
        }
        /*try{
            Statement stmt = conn.createStatement();
            myBool = stmt.execute(sql);

            stmt.close();
        }catch(Exception e){e.printStackTrace();}*/
        System.out.println(sql);
        return true;
    }

    public HashMap<String,Integer> getLocales(){
        HashMap<String,Integer> ret = new HashMap<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery( "SELECT * FROM compteurweb.locales" );
            String Pays ="";
            int GMT =0;
            while ( resultat.next() ) {
                Pays = resultat.getString("Pays");
                GMT = resultat.getInt("GMT");
                ret.put(Pays,GMT);
            }
            stmt.close();
        }catch(Exception e){e.printStackTrace();}
        return ret;
    }

    private HashMap<String,Integer> parse()  {

        String line ="";
        String capitale ="";
        String pays="";
        String[] tabListeCap;

        InputStream f = this.getClass().getResourceAsStream("/cities.txt");
        InputStream f2 = this.getClass().getResourceAsStream("/ListeCapitale.csv");

        Scanner sc = new Scanner(f);
        Scanner sc2 = new Scanner(f2);

        HashMap<String,Integer> hmRes = new HashMap<String, Integer>();
        HashMap<String,String> hmCap = new HashMap<String, String>();

        String listeCap = sc2.nextLine();

        while (sc2.hasNextLine()) {

            listeCap = sc2.nextLine();
            listeCap = listeCap.replace("\"", "");
            tabListeCap = listeCap.split(",");

            hmCap.put(tabListeCap[1],tabListeCap[0]);
        }

        while (sc.hasNextLine()) {
            line = sc.next();
            pays ="";
            String signe = "#";

            if(line.contains("GMT")) {

                String[] temp = line.split(",");

                capitale = temp[0].replace("\"","");

                for(Map.Entry<String, String> entry : hmCap.entrySet()) {

                    String cap = entry.getKey();
                    String country = entry.getValue();

                    if(cap.equals(capitale))
                        pays = country;
                }

                if (! pays.equals("")) {

                    int intGmt =0;

                    if (temp[1].contains("+")) {
                        signe = "\\+";
                        String gmt = temp[1].split(signe)[1];
                        gmt = gmt.split(":")[0];
                        intGmt = new Integer(gmt);
                    } else {
                        if (temp[1].contains("-")) {
                            signe = "-";
                            String gmt = temp[1].split(signe)[1];
                            gmt = gmt.split(":")[0];
                            int tmp = Integer.parseInt(gmt)*-1;
                            intGmt = tmp;
                        }
                    }

                    hmRes.put(pays, intGmt);

                }
            }
            sc.nextLine();
        }

        return hmRes;
    }

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
    }
}
