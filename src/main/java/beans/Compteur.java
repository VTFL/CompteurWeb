package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by thiba on 08/11/2016.
 */
public class Compteur {
    private int id;
    private String titre;
    private String pays;
    private String date;
    private int idSession;
    private int gmt;
    private String majCompteur;

    public Compteur(int id, String titre, String pays, String date, int idSession) {
        this.id =id;
        this.titre = titre;
        this.pays = pays;
        this.date = date;
        this.gmt=0;
        this.idSession = idSession;
        this.majCompteur=null;
    }

    public String getMajCompteur() {
        return majCompteur;
    }

    public void setMajCompteur(String maj) {
        this.majCompteur = maj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getGmt() {
		return gmt;
	}

	public void setGmt(int gmt) {
		this.gmt = gmt;
	}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(int gmt) {
        this.pays = pays;
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

    public String diff(){
        //String pattern = "dd/MM/yyyy HH:mm:ss";

		LocalDateTime d1 = LocalDateTime.parse(date);
		ZonedDateTime d2 = ZonedDateTime.now(ZoneOffset.UTC );
		LocalDateTime res=null;

		System.out.println(d2);
		System.out.println(d1);

		d2 = d2.plusHours(gmt);

		res = d1.minusSeconds(d2.getSecond());
		res = res.minusMinutes(d2.getMinute());
		res = res.minusHours(d2.getHour());
		res = res.minusDays(d2.getDayOfYear());
		res = res.minusYears(d2.getYear());

		long diffSeconds = res.getSecond();
		long diffMinutes = res.getMinute();
		long diffHours = res.getHour();
		long diffDays = res.getDayOfYear() + res.getYear()*365;

        return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";

    }


    @Override
    public String toString() {
        return "Compteur{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", pays=" + pays +
                ", date=" + date +
                ", idSession=" + idSession +
                '}';
    }
}
