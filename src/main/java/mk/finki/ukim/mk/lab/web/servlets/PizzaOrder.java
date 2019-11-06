package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "pizza-order-servlet", urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext webContext = new WebContext(request, response, request.getServletContext());
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        webContext.setVariable("pizza_size", session.getAttribute("pizza_size"));
        webContext.setVariable("pizza_type", session.getAttribute("pizza_type"));
        this.springTemplateEngine.process("deliveryInfo.html", webContext, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("clientName", req.getParameter("clientName"));
        session.setAttribute("clientAddress", req.getParameter("clientAddress"));
        session.setAttribute("clientIp", req.getRemoteAddr());
        session.setAttribute("clientBrowser", req.getHeader("User-Agent"));
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        resp.sendRedirect("/ConfirmationInfo.do");
    }
}
