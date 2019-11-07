package mk.finki.ukim.mk.lab.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        System.out.println("[WP-Log" + request.getRequestURI() + " method " + request.getMethod());

    }

    @Override
    public void requestDestroyed(ServletRequestEvent event){

    }

}
