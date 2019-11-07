package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Pizza;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="select-pizza-servlet", urlPatterns = "/SelectPizzaSize.do")
public class SelectPizza extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public SelectPizza(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        webContext.setVariable("pizza_type", session.getAttribute("pizza_type"));
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("pizza_size", req.getParameter("pizza_size"));
        resp.sendRedirect("/PizzaOrder.do");
    }
}
