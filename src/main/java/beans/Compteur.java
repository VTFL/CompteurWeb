package beans;

import java.sql.Date;

/**
 * Created by thiba on 08/11/2016.
 */
public class Compteur {
    private int id;
    private String titre;
    private int gmt;
    private Date date;
    private int idSession;

    public Compteur(int id, String titre, int gmt, Date date, int idSession) {

        this.id = id;
        this.titre = titre;
        this.gmt = gmt;
        this.date = date;
        this.idSession = idSession;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }



    @Override
    public String toString() {
        return "Compteur{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", gmt=" + gmt +
                ", date=" + date +
                ", idSession=" + idSession +
                '}';
    }
}
