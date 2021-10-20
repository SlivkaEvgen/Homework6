package org.homework.servlet.company;

import org.homework.model.Company;
import org.homework.service.CrudService;
import org.homework.service.ServiceFactory;
import org.homework.servlet.CrudView;
import org.homework.servlet.CrudViewImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/company/*"})
public class AddNewCompanyServlet extends HttpServlet {

    private final CrudView<Company, Long> addViewImpl = new CrudViewImpl<>(Company.class);
    private final CrudService<Company, Long> service = ServiceFactory.of(Company.class);
    private static AddNewCompanyServlet addNewCompanyServlet;

    public static AddNewCompanyServlet getInstance() {
        if (addNewCompanyServlet == null) {
            addNewCompanyServlet = new AddNewCompanyServlet();
        }
        return addNewCompanyServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addViewImpl.addNewDoGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("name");

        if (!Validator.validId(id)) {
            req.setAttribute("error", "Wrong ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validName(name)) {
            req.setAttribute("error", "Wrong Name");
            req.setAttribute("error2", "Enter correct Name ");
            doGet(req, resp);
        } else if (!Validator.validName(city)) {
            req.setAttribute("error", "Wrong City");
            req.setAttribute("error2", "Enter correct City ");
            doGet(req, resp);
        } else if (new Validator<>(Company.class).validUniqueId(id)) {
            req.setAttribute("error", " ID " + id + " duplicates an existing one ! \n ");
            req.setAttribute("error2", "\n  Enter another ID ");
            doGet(req, resp);
        } else {
            service.create(new Company(Long.valueOf(id), name, city));
            addViewImpl.updatePostDo(req, resp);
        }
    }
}
