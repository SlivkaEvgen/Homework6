package org.homework.servlet.developer;

import lombok.SneakyThrows;
import org.homework.service.DeveloperService;
import org.homework.service.DeveloperServiceImpl;
import org.homework.service.ProjectServiceImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/fromOneProject/"})
public class FromOneProject extends HttpServlet {

    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private static FromOneProject fromOneProject;

    public static FromOneProject getInstance() {
        if (fromOneProject == null) {
            fromOneProject = new FromOneProject();
        }
        return fromOneProject;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/developer/developersFromOneProject.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String projectId = req.getParameter("id");
        if (Validator.validId(projectId)) {
            if (ProjectServiceImpl.getProjectService().findById(Long.valueOf(projectId)).isPresent()) {
                req.setAttribute("list", service.getDevelopersFromOneProject(Long.valueOf(projectId)));
                req.getServletContext().getRequestDispatcher("/WEB-INF/view/developer/developersFromOneProject.jsp")
                        .forward(req, resp);
                resp.sendRedirect(req.getContextPath() + "/fromOneProject/");
            } else {
                req.setAttribute("error", "Not found Project by ID = " + projectId);
                doGet(req, resp);
            }
        } else {
            req.setAttribute("error", "Not found Project by ID = " + projectId);
            doGet(req, resp);
        }
    }
}
