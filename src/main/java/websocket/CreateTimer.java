package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/CreateTimer")
public class CreateTimer {

    // A chaque fois qu'on reçoit un nouveau message de la part du client on cré un nouveau Timer
    // on stock le Timer
    // et on met en place la boucle qui envoi au client le timer mis a jour toute les secondes
    @OnMessage
    public void onMessage(Session session, String msg, boolean last) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText("message :"+msg, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }


}