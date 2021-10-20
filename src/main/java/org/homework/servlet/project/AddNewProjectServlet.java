package org.homework.servlet.project;

import org.homework.model.Company;
import org.homework.model.Customer;
import org.homework.model.Project;
import org.homework.service.ProjectService;
import org.homework.service.ProjectServiceImpl;
import org.homework.service.ServiceFactory;
import org.homework.servlet.CrudView;
import org.homework.servlet.CrudViewImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet(urlPatterns = {"/project/*"})
public class AddNewProjectServlet extends HttpServlet {

    private final CrudView<Project, Long> addViewImpl = new CrudViewImpl<>(Project.class);
    private final ProjectService service = ProjectServiceImpl.getProjectService();
    private static AddNewProjectServlet addNewProjectServlet;

    public static AddNewProjectServlet getInstance() {
        if (addNewProjectServlet == null) {
            addNewProjectServlet = new AddNewProjectServlet();
        }
        return addNewProjectServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addViewImpl.addNewDoGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String cost = req.getParameter("cost");
        String companyId = req.getParameter("companyId");
        String customerId = req.getParameter("customerId");

        if (!Validator.validId(id)) {
            req.setAttribute("error", "Wrong ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validName(name)) {
            req.setAttribute("error", "wrong name");
            req.setAttribute("error2", "Enter correct Name");
            doGet(req, resp);
        } else if (!Validator.validNumber(cost)) {
            req.setAttribute("error", "Wrong cost");
            req.setAttribute("error2", "Enter correct Cost");
            doGet(req, resp);
        } else if (!Validator.validId(companyId)) {
            req.setAttribute("error", "Wrong Company ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validId(customerId)) {
            req.setAttribute("error", "Wrong Customer ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!ServiceFactory.of(Company.class).findById(Long.valueOf(companyId)).isPresent()) {
            req.setAttribute("error", "Company with ID - " + companyId + " not found");
            req.setAttribute("error2", "Choose another Company ");
            doGet(req, resp);
        } else if (!ServiceFactory.of(Customer.class).findById(Long.valueOf(customerId)).isPresent()) {
            req.setAttribute("error", "Customer with ID - " + customerId + " not found");
            req.setAttribute("error2", "Choose another Customer ");
            doGet(req, resp);
        } else if (new Validator<>(Project.class).validUniqueId(id)) {
            req.setAttribute("error", " ID " + id + " duplicates an existing one ! \n ");
            req.setAttribute("error2", "\n  Enter another ID ");
            doGet(req, resp);
        } else {
            service.create(new Project(Long.valueOf(id), name, Long.valueOf(cost), new Date().toString(),
                    Long.valueOf(companyId), Long.valueOf(customerId)));
            addViewImpl.updatePostDo(req, resp);
        }
    }
}

