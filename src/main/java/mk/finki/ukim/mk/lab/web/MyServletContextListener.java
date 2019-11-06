package mk.finki.ukim.mk.lab.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
