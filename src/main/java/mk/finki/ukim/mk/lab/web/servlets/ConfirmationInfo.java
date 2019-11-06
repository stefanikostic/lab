package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="confirmation-info-servlet", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;

     public ConfirmationInfo(SpringTemplateEngine springTemplateEngine){
         this.springTemplateEngine = springTemplateEngine;
     }

     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         WebContext webContext = new WebContext(request, response, request.getServletContext());
         response.setContentType("text/html; charset=UTF-8");
         HttpSession session = request.getSession();
         webContext.setVariable("clientName", session.getAttribute("clientName"));
         webContext.setVariable("clientAddress", session.getAttribute("clientAddress"));
         webContext.setVariable("clientIp", session.getAttribute("clientIp"));
         webContext.setVariable("clientBrowser", session.getAttribute("clientBrowser"));
         webContext.setVariable("pizza_type", session.getAttribute("pizza_type"));
         webContext.setVariable("pizza_size", session.getAttribute("pizza_size"));
         this.springTemplateEngine.process("confirmationInfo.html", webContext, response.getWriter());
     }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/logout");
    }
}
