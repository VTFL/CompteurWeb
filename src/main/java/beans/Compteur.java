package beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by thiba on 08/11/2016.
 */
public class Compteur {
    private int id;
    private String titre;
    private int gmt;
    private String date;
    private int idSession;
    private String majCompteur;

    public Compteur(int id, String titre, int gmt, String date, int idSession) {

        this.id = id;
        this.titre = titre;
        this.gmt = gmt;
        this.date = date;
        this.idSession = idSession;
        this.majCompteur=null;
    }

    public String getMajCompteur(){ return majCompteur;}
    public void setMajCompteur(String s){this.majCompteur=s;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getGmt() {
        return gmt;
    }

    public void setGmt(int gmt) {
        this.gmt = gmt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    private String diff(){
        String theDate = "08/11/2016 17:30:00";
        String pattern = "dd/MM/yyyy HH:mm:ss";
        java.util.Date d2 = null;
        try {
            d2 = new SimpleDateFormat(pattern).parse(theDate);
        } catch (ParseException e) {
            //return "server error...";
        }
        java.util.Date d1 = new java.util.Date();

        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";

    }


    @Override
    public String toString() {
        return "Compteur{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", gmt=" + gmt +
                ", date=" + date +
                ", idSession=" + idSession +
                ", majCompteur=" + majCompteur +
                '}';
    }
}
