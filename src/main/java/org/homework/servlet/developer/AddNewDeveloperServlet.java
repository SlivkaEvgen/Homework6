package org.homework.servlet.developer;

import lombok.SneakyThrows;
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

@WebServlet(urlPatterns = {"/developer/*"})
public class AddNewDeveloperServlet extends HttpServlet {

    private final CrudView<Developer, Long> addViewImpl = new CrudViewImpl<>(Developer.class);
    private final DeveloperService service = DeveloperServiceImpl.getDeveloperService();
    private static AddNewDeveloperServlet addNewDeveloperServlet;

    public static AddNewDeveloperServlet getInstance() {
        if (addNewDeveloperServlet == null) {
            addNewDeveloperServlet = new AddNewDeveloperServlet();
        }
        return addNewDeveloperServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addViewImpl.addNewDoGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String salary = req.getParameter("salary");
        String companyId = req.getParameter("companyId");

        if (!Validator.validId(id)) {
            req.setAttribute("error", "Wrong ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validName(name)) {
            req.setAttribute("error", "Wrong Name");
            req.setAttribute("error2", "Enter correct Name");
            doGet(req, resp);
        } else if (!Validator.validNumber(age)) {
            req.setAttribute("error", "Wrong Age");
            req.setAttribute("error2", "Enter correct Age");
            doGet(req, resp);
        } else if (!Validator.validId(companyId)) {
            req.setAttribute("error", "Wrong Company ID");
            req.setAttribute("error2", "Enter correct ID");
            doGet(req, resp);
        }else if (!ServiceFactory.of(Company.class).findById(Long.valueOf(companyId)).isPresent()) {
            req.setAttribute("error", "Company with ID - " + companyId + " not found");
            req.setAttribute("error2", "Choose another Company ");
            doGet(req, resp);
        } else if (!Validator.validGender(gender)) {
            req.setAttribute("error", "Gender Wrong");
            req.setAttribute("error2", "Enter : Male or Female ");
            doGet(req, resp);
        } else if (!Validator.validEmail(email)) {
            req.setAttribute("error", "Wrong Email");
            req.setAttribute("error2", " Must have : '@' ");
            doGet(req, resp);
        } else if (!Validator.validNumber(salary)) {
            req.setAttribute("error", "Wrong Salary");
            req.setAttribute("error2", " Use Only Numbers");
            doGet(req, resp);
        } else if (new Validator<>(Developer.class).validUniqueId(id)) {
            req.setAttribute("error", " ID " + id + " duplicates an existing one ! \n ");
            req.setAttribute("error2", "\n  Enter another ID ");
            doGet(req, resp);
        } else {
            service.create(new Developer(Long.valueOf(id), name, Long.valueOf(age),
                    gender, email, Long.valueOf(salary), Long.valueOf(companyId)));
            addViewImpl.updatePostDo(req, resp);
        }
    }
}

