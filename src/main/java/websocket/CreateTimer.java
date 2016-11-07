package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import org.json.JSONObject;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/websocket/CreateTimer")
public class CreateTimer {
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
    private static Set<Session> allSessions;
    // A chaque fois qu'on reçoit un nouveau message de la part du client on cré un nouveau Timer
    // on stock le Timer dans la base avec sont userID
    // et on met en place la boucle qui envoi au client les timers mis a jour toutes les secondes

    @OnMessage
    public void onMessage(Session session, String jsonMessage) {
        try {
            if (session.isOpen()) {
                JSONObject data = new JSONObject(jsonMessage);

				session.getBasicRemote().sendText(data.getString("userID"));
                session.getId();
            }
        } catch (Exception e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }
    @OnOpen
    public void showTimer(Session session){
        // on envoie toute les secondes
        timer.scheduleAtFixedRate(
                () -> sendTimeToAll(session),0,1, TimeUnit.SECONDS);
    }
    private void sendTimeToAll(Session session){
        allSessions = session.getOpenSessions();
        for (Session sess: allSessions){
            try{
                sess.getBasicRemote().sendText("temps restant :"+diff());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
    // on changera pour mettre une date non fixe
    private String diff(){
        String theDate = "08/11/2016 17:30:00";
        String pattern = "dd/MM/yyyy HH:mm:ss";
        Date d2 = null;
        try {
            d2 = new SimpleDateFormat(pattern).parse(theDate);
        } catch (ParseException e) {
            return "server error...";
        }
        Date d1 = new Date();

        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";

    }



}