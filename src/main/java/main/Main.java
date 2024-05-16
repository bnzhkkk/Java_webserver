package main;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import account.Serv;
import account.ServController;
import account.ServControllerMBean;
import account.ServIMPL;
import servlets.AdminServlet;


public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        logger.info("Starting at http://127.0.0.1:" + 8080);

        Serv serv = new ServIMPL();

        ServControllerMBean servStatistics = new ServController(serv);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ServController");
        mbs.registerMBean(servStatistics, name);

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminServlet(serv)), AdminServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);


        server.start();
        logger.info("Server started");
        Thread taskThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                serv.setUsersLimit(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        taskThread.start();

        server.join();
    }
}