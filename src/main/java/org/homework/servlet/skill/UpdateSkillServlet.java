package org.homework.servlet.skill;

import org.homework.model.Skill;
import org.homework.service.CrudService;
import org.homework.service.ServiceFactory;
import org.homework.servlet.CrudView;
import org.homework.servlet.CrudViewImpl;
import org.homework.util.Validator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/skill/skill/*"})
public class UpdateSkillServlet extends HttpServlet {

    private final CrudService<Skill, Long> service = ServiceFactory.of(Skill.class);
    private final CrudView<Skill, Long> updateDoGet = new CrudViewImpl<>(Skill.class);
    private static UpdateSkillServlet updateSkillServlet;

    public static UpdateSkillServlet getInstance() {
        if (updateSkillServlet == null) {
            updateSkillServlet = new UpdateSkillServlet();
        }
        return updateSkillServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        updateDoGet.updateGetDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String activity = req.getParameter("activity");
        String level = req.getParameter("level");

        if (!Validator.validName(activity)) {
            req.setAttribute("error", "wrong activity");
            doGet(req, resp);
        }else if (!Validator.validName(level)) {
            req.setAttribute("error", "wrong level");
            doGet(req, resp);
        }else if (Validator.validActivity(activity)) {
            req.setAttribute("error", "wrong activity");
            req.setAttribute("error2", "Try : Java, JS, C+, C#");
            doGet(req, resp);
        }else if (Validator.validLevel(level)) {
            req.setAttribute("error", "wrong level");
            req.setAttribute("error2", "Try : Junior, Middle, Senior ");
            doGet(req, resp);
        }else {
            service.update(new Skill(Long.valueOf(req.getParameter("id")), activity, level));
            updateDoGet.updatePostDo(req, resp);
        }
    }
}
