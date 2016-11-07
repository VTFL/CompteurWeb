package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import java.io.IOException;
import java.util.HashMap;

import org.json.*;
import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/CreateTimer")
public class CreateTimer {

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



}