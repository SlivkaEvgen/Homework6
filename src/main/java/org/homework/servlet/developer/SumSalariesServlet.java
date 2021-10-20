package org.homework.servlet.developer;

import lombok.SneakyThrows;
import org.homework.service.DeveloperService;
import org.homework.service.DeveloperServiceImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/sumSalaries/*"})
public class SumSalariesServlet extends HttpServlet {

    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private static SumSalariesServlet sumSalariesServlet;

    public static SumSalariesServlet getInstance() {
        if (sumSalariesServlet == null) {
            sumSalariesServlet = new SumSalariesServlet();
        }
        return sumSalariesServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/developer/sumSalariesView.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String projectId = req.getParameter("projectId");
        if (Validator.validNumber(projectId)) {
            req.setAttribute("sumSalaries", service.getSumSalariesDevelopersOfOneProject(Long.valueOf(projectId)));
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/developer/sumSalariesView.jsp")
                    .forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/sumSalaries/");
        } else {
            req.setAttribute("error", " Not found Project ");
            doGet(req, resp);
        }
    }
}
