package org.homework.servlet.developer;

import org.homework.model.Company;
import org.homework.model.Developer;
import org.homework.service.DeveloperService;
import org.homework.service.DeveloperServiceImpl;
import org.homework.service.ServiceFactory;
import org.homework.servlet.CrudView;
import org.homework.servlet.CrudViewImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/developer/developer/*"})
public class UpdateDeveloperServlet extends HttpServlet {

    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private final CrudView<Developer, Long> updateDoGet = new CrudViewImpl<>(Developer.class);
    private static UpdateDeveloperServlet updateDeveloperServlet;

    public static UpdateDeveloperServlet getInstance() {
        if (updateDeveloperServlet == null) {
            updateDeveloperServlet = new UpdateDeveloperServlet();
        }
        return updateDeveloperServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        updateDoGet.updateGetDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String salary = req.getParameter("salary");
        String companyId = req.getParameter("companyId");

        if (!Validator.validName(name)) {
            req.setAttribute("error", "Wrong Name");
            req.setAttribute("error2", "\n  Enter correct Name ");
            doGet(req, resp);
        } else if (!Validator.validNumber(age)) {
            req.setAttribute("error", "Wrong Age");
            req.setAttribute("error2", "\n  Enter correct Age ");
            doGet(req, resp);
        } else if (Validator.validGender(gender)) {
            req.setAttribute("error", "Wrong Gender");
            req.setAttribute("error2", "Enter : Male or Female ");
            doGet(req, resp);
        } else if (!Validator.validEmail(email)) {
            req.setAttribute("error", "Wrong Email");
            req.setAttribute("error2", "Must have: '@' ");
            doGet(req, resp);
        } else if (!Validator.validNumber(salary)) {
            req.setAttribute("error", "Wrong Salary");
            req.setAttribute("error2", "\n  Enter correct Salary ");
            doGet(req, resp);
        } else if (!Validator.validId(companyId)) {
            req.setAttribute("error", "Wrong Company ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!ServiceFactory.of(Company.class).findById(Long.valueOf(companyId)).isPresent()) {
            req.setAttribute("error", "Not found Company " + companyId);
            doGet(req, resp);
        } else {
            service.update(new Developer(Long.valueOf(req.getParameter("id")), name, Long.valueOf(age), gender, email, Long.valueOf(salary), Long.valueOf(companyId)));
            updateDoGet.updatePostDo(req, resp);
        }
    }
}
