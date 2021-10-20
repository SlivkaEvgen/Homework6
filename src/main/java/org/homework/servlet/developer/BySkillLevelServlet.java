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

@WebServlet(urlPatterns = {"/bySkillLevel/*"})
public class BySkillLevelServlet extends HttpServlet {

    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private static BySkillLevelServlet bySkillLevelServlet;

    public static BySkillLevelServlet getInstance() {
        if (bySkillLevelServlet == null) {
            bySkillLevelServlet = new BySkillLevelServlet();
        }
        return bySkillLevelServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/developer/developersBySkillLevel.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        List<Developer> developerList = service.getDevelopersByLevel(req.getParameter("level"));
        if (!developerList.isEmpty()) {
            req.setAttribute("list", developerList);
            req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/developer/developersBySkillLevel.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/bySkillLevel");
        } else {
            req.setAttribute("error", " Not found Developers by Level");
            doGet(req, resp);
        }
    }
}
