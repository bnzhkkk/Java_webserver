package servlets;

import resourceServ.ResourceServ;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResourceServlet extends HttpServlet {
    public static final String PAGE_URL = "/resources";
    private final ResourceServ resourceServ;

    public ResourceServlet(ResourceServ resourceServ) {
        this.resourceServ = resourceServ;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getParameter("path");
        response.setContentType("text/html;charset=utf-8");
        resourceServ.readResource(path);
        response.getWriter().printf("Loaded: " + path + "<br>");
        response.getWriter().printf(
                "Class name: " + resourceServ.getNameClass() + "<br>" +
                        "Name: " + resourceServ.getName() + "<br>" +
                        "Age: " + resourceServ.getAge() + "<br>"
        );
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getParameter("path");
        response.setContentType("text/html;charset=utf-8");
        resourceServ.readResource(path);
        response.getWriter().printf("Loaded class " + resourceServ.getNameClass() + "<br> from path: " + path + "<br>");
        response.getWriter().printf(
                "Class name: " + resourceServ.getNameClass() + "<br>" +
                        "Name: " + resourceServ.getName() + "<br>" +
                        "Age: " + resourceServ.getAge() + "<br>"
        );
        response.setStatus(HttpServletResponse.SC_OK);
    }

}