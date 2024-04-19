package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class Chat {
    private Session session;
    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
    }
    @OnWebSocketMessage
    public void onMessage(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
