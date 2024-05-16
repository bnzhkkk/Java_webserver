package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import account.Serv;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";
    private Serv serv;
    private Logger logger = LogManager.getLogger(AdminServlet.class.getName());


    public AdminServlet(Serv accountServer) {
        this.serv = accountServer;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        int limit = serv.getUsersLimit();
        logger.info("Limit: " + limit);
        response.getWriter().println(limit);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}