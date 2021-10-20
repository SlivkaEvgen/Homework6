package org.homework.servlet.developer;

import lombok.SneakyThrows;
import org.homework.model.Developer;
import org.homework.service.DeveloperService;
import org.homework.service.DeveloperServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/bySkillActivity/*"})
public class BySkillActivityServlet extends HttpServlet {

    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private static BySkillActivityServlet bySkillActivityServlet;

    public static BySkillActivityServlet getInstance() {
        if (bySkillActivityServlet == null) {
            bySkillActivityServlet = new BySkillActivityServlet();
        }
        return bySkillActivityServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/developer/developersBySkillActivity.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        List<Developer> developerList = service.getDevelopersByActivity(req.getParameter("activity"));
        if (!developerList.isEmpty()) {
            req.setAttribute("list", developerList);
            req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/developer/developersBySkillActivity.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/bySkillActivity");
        } else {
            req.setAttribute("error", " Not found Developers by Activity");
            doGet(req, resp);
        }
    }
}
