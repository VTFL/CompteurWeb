package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import beans.Compteur;
import com.google.gson.Gson;
import db.DatabaseManager;
import org.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint(value="/websocket/CreateTimer",configurator=ConfigurationWs.class)
public class CreateTimer {
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
    private static Set<Session> allSessions;
    private DatabaseManager dbm = null;
    // A chaque fois qu'on reçoit un nouveau message de la part du client on crée un nouveau Timer
    // on stock le Timer dans la base avec sont userID
    // et on met en place la boucle qui envoi au client les timers mis a jour toutes les secondes

    @OnMessage
    public void onMessage(Session session, String jsonMessage) {
        try {
            if (session.isOpen()) {
               JSONObject data = new JSONObject(jsonMessage);

                /*SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss ");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                String newDateFormat = sdf1.format(sdf2.parse(data.getString("echeance")))
                        .toString();*/
                if(data.getString("action").equals("add"))
                    dbm.ajouterCompteur(new Compteur(0,
                            data.getString("titre"),
                            data.getString("pays"),
                            data.getString("echeance"),
                            data.getInt("idSession")));
                else if(data.getString("action").equals("remove")) {

                    dbm.supprimerCompteur(data.getInt("id"));

                }
                else if (data.getString("action").equals("random")) {

                    Random r = new Random();
                    final int MILLIS_IN_SECOND = 1000;
                    final int SECONDS_IN_MINUTE = 60;
                    final int MINUTES_IN_HOUR = 60;
                    final int HOURS_IN_DAY = 24;
                    final int DAYS_IN_YEAR = 365; //I know this value is more like 365.24...
                    final long MILLISECONDS_IN_YEAR = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;

                    long number = MILLISECONDS_IN_YEAR+((long)(r.nextDouble()*(100*MILLISECONDS_IN_YEAR-MILLISECONDS_IN_YEAR)));

                    Date d = new Date(System.currentTimeMillis() + number);
                    String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.FRENCH).format(d);

                    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                    StringBuilder pass = new StringBuilder(5);
                    for (int x = 0; x < 5; x++) {
                        int i = (int) (Math.random() * chars.length());
                        pass.append(chars.charAt(i));
                    }

                    dbm.ajouterCompteur(new Compteur(0,
                            "Compteur Aléatoire : " + pass.toString(),
                            data.getString("pays"),
                            format,
                            data.getInt("idSession")));
                }

                /**/

                        //.toString();
                /*if(isValidFormat("dd/MM/yyyy kk:mm:ss ",newDateFormat))
                    dbm.ajouterCompteur(new Compteur(0,data.getString("titre"),
                            data.getString("pays"),newDateFormat,data.getInt("idSession")));*/



                session.getId();
            }
        } catch (Exception e) {
            try {
                System.out.println(e);
                if (session.isOpen())
                    session.close();
            } catch (IOException e1) {
                System.out.println(e1);
            }
        }
    }
    @OnClose
    public void onClose(final Session session) {
       System.out.println("Close connection for client: "+
                session.getId());

    }
    @OnOpen
    public void showTimer(Session session){
        dbm= new DatabaseManager();

        // on envoie toute les secondes
        timer.scheduleAtFixedRate(
                () -> sendTimeToAll(session),0,1, TimeUnit.SECONDS);
    }

    private void sendTimeToAll(Session session ){

        ArrayList<Compteur> lst_compteur = new ArrayList<>();
        try{
            if (session.isOpen()) {
                Gson gson = new Gson();
                //session.getUserProperties().get("cookie")[0];
                String cookies[] = session.getUserProperties().get("cookie")
                        .toString().split(";");
                String userID = "";
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].contains("userID"))
                        userID = cookies[i];
                }
                userID = userID.substring(userID.indexOf("=") + 1);
                if (userID.contains("]"))
                    userID = userID.substring(userID.indexOf("=") + 1, userID.length() - 1);
                else
                    userID = userID.substring(userID.indexOf("=") + 1);
                lst_compteur = dbm.getCompteurs(Integer.parseInt(userID));
                Compteur c;
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss ");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                for (int i = 0; i < lst_compteur.size(); i++) {
                    c = lst_compteur.get(i);
                    c.setMajCompteur(c.diff());
                    if(isValidFormat("yyyy-MM-dd'T'HH:mm",c.getDate()))
                        c.setDate(sdf1.format(sdf2.parse(c.getDate())));
                }
                String json = gson.toJson(lst_compteur);
                session.getBasicRemote().sendText(json);
            }else{
                if (session.isOpen())
                    session.close();
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
              SimpleDateFormat sdf = new SimpleDateFormat(format);
           date = sdf.parse(value);
              if (!value.equals(sdf.format(date))) {
                        date = null;
                    }
            } catch (ParseException ex) {
               System.out.println(ex);
        }
        return date != null;
    }

}