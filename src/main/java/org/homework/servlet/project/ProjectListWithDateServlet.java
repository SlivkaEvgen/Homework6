package org.homework.servlet.project;

import lombok.SneakyThrows;
import org.homework.service.ProjectService;
import org.homework.service.ProjectServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/projectWithDate/"})
public class ProjectListWithDateServlet extends HttpServlet {

    private final ProjectService service = ProjectServiceImpl.getProjectService();
    private static ProjectListWithDateServlet projectListWithDateServlet;

    public static ProjectListWithDateServlet getInstance() {
        if (projectListWithDateServlet == null) {
            projectListWithDateServlet = new ProjectListWithDateServlet();
        }
        return projectListWithDateServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("projectsWithDate", service.getListProjectsWithDate());
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/project/projectListWithDateView.jsp")
                .forward(req, resp);
    }
}