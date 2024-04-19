package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class ChatServlet extends WebSocketServlet {
    private final static int LOGOFF_TIME = 60 * 10 *1000;

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOFF_TIME);
        factory.setCreator((req, resp) -> new Chat());
    }
}