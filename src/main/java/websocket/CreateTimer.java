package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import beans.Compteur;
import com.google.gson.Gson;
import db.DatabaseManager;
import org.json.JSONObject;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/websocket/CreateTimer")
public class CreateTimer {
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
    private static Set<Session> allSessions;
    private DatabaseManager dbm = null;
    // A chaque fois qu'on reçoit un nouveau message de la part du client on cré un nouveau Timer
    // on stock le Timer dans la base avec sont userID
    // et on met en place la boucle qui envoi au client les timers mis a jour toutes les secondes

    @OnMessage
    public void onMessage(Session session, String jsonMessage) {
        try {
            if (session.isOpen()) {

                JSONObject data = new JSONObject(jsonMessage);

				dbm.ajouterCompteur(new Compteur(0,data.getString("titre")
						,Integer.parseInt(data.getString("gmt"))
						,data.getString("date")
						,Integer.parseInt(data.getString("idSession"))));
            }
        } catch (Exception e) {
            try {
				System.out.println(e);
                session.close();
            } catch (IOException e1) {
				System.out.println(e1);
			}
        }
    }
    //faire en fonction des cookies
    @OnOpen
    public void showTimer(Session session){
        dbm= new DatabaseManager();
        // on envoie toute les secondes
        timer.scheduleAtFixedRate(
                () -> sendTimeToAll(session),0,1, TimeUnit.SECONDS);
    }

    private void sendTimeToAll(Session session){
       // allSessions = session.getOpenSessions();
        ArrayList<Compteur> lst_compteur = new ArrayList<>();
      //  for (Session sess: allSessions){
        try{
            Gson gson = new Gson();
            lst_compteur=dbm.getCompteurs(Integer.parseInt(session.getId()));
            Compteur c ;
            for(int i = 0; i<lst_compteur.size();i++){
                c=lst_compteur.get(i);
                c.setMajCompteur(c.diff());
            }
            String json = gson.toJson(lst_compteur);
            session.getBasicRemote().sendText(json);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}