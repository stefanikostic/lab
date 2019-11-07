package mk.finki.ukim.mk.lab.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[WP-log] contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
