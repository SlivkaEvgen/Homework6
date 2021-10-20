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

@WebServlet(urlPatterns = {"/company/company/*"})
public class UpdateCompanyServlet extends HttpServlet {

    private final CrudService<Company, Long> service = ServiceFactory.of(Company.class);
    private final CrudView<Company, Long> updateDoGet = new CrudViewImpl<>(Company.class);
    private static UpdateCompanyServlet updateCompanyServlet;

    public static UpdateCompanyServlet getInstance() {
        if (updateCompanyServlet == null) {
            updateCompanyServlet = new UpdateCompanyServlet();
        }
        return updateCompanyServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        updateDoGet.updateGetDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String city = req.getParameter("city");

        if (!Validator.validName(name)) {
            req.setAttribute("error", "Wrong Name");
            req.setAttribute("error2", "\n  Enter correct Name ");
            doGet(req, resp);
        } else if (!Validator.validName(city)) {
            req.setAttribute("error", "Wrong City");
            req.setAttribute("error2", "\n  Enter correct City ");
            doGet(req, resp);
        } else {
            service.update(new Company(Long.valueOf(req.getParameter("id")), name,city));
            updateDoGet.updatePostDo(req, resp);
        }
    }
}