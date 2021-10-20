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

@WebServlet(urlPatterns = {"/skill/*"})
public class AddNewSkillServlet extends HttpServlet {

    private final CrudView<Skill, Long> addViewImpl = new CrudViewImpl<>(Skill.class);
    private final CrudService<Skill, Long> service = ServiceFactory.of(Skill.class);
    private static AddNewSkillServlet addNewSkillServlet;

    public static AddNewSkillServlet getInstance() {
        if (addNewSkillServlet == null) {
            addNewSkillServlet = new AddNewSkillServlet();
        }
        return addNewSkillServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addViewImpl.addNewDoGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String activity = req.getParameter("activity");
        String level = req.getParameter("level");
        if (!Validator.validId(id)) {
            req.setAttribute("error", "Wrong ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validName(activity)) {
            req.setAttribute("error", "wrong activity");
            doGet(req, resp);
        } else if (!Validator.validName(level)) {
            req.setAttribute("error", "wrong level");
            doGet(req, resp);
        } else if (Validator.validActivity(activity)) {
            req.setAttribute("error", "wrong activity");
            req.setAttribute("error2", "Try : Java, JS, C+, C#");
            doGet(req, resp);
        } else if (Validator.validLevel(level)) {
            req.setAttribute("error", "wrong level");
            req.setAttribute("error2", "Try : Junior, Middle, Senior ");
            doGet(req, resp);
        } else if (new Validator<>(Skill.class).validUniqueId(id)) {
            req.setAttribute("error", " ID " + id + " duplicates an existing one ! \n ");
            req.setAttribute("error2", "\n  Enter another ID ");
            doGet(req, resp);
        } else {
            service.create(new Skill(Long.valueOf(id), req.getParameter("activity"), req.getParameter("level")));
            addViewImpl.updatePostDo(req, resp);
        }
    }
}
