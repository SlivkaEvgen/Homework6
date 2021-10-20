package org.homework.servlet.customer;

import org.homework.model.Customer;
import org.homework.service.CrudService;
import org.homework.service.ServiceFactory;
import org.homework.servlet.CrudView;
import org.homework.servlet.CrudViewImpl;
import org.homework.util.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/customer/*"})
public class AddNewCustomerServlet extends HttpServlet {

    private final CrudView<Customer, Long> addViewImpl = new CrudViewImpl<>(Customer.class);
    private final CrudService<Customer, Long> service = ServiceFactory.of(Customer.class);
    private static AddNewCustomerServlet addNewCustomerServlet;

    public static AddNewCustomerServlet getInstance() {
        if (addNewCustomerServlet == null) {
            addNewCustomerServlet = new AddNewCustomerServlet();
        }
        return addNewCustomerServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addViewImpl.addNewDoGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String budget = req.getParameter("budget");

        if (!Validator.validId(id)) {
            req.setAttribute("error", "Wrong ID");
            req.setAttribute("error2", "\n  Enter correct ID ");
            doGet(req, resp);
        } else if (!Validator.validName(name)) {
            req.setAttribute("error", "Wrong Name");
            req.setAttribute("error2", "\n  Enter correct Name ");
            doGet(req, resp);
        } else if (!Validator.validName(city)) {
            req.setAttribute("error", "Wrong City");
            req.setAttribute("error2", "\n  Enter correct City ");
            doGet(req, resp);
        } else if (!Validator.validNumber(budget)) {
            req.setAttribute("error", "Wrong Budget");
            req.setAttribute("error2", "\n  Enter correct Budget ");
            doGet(req, resp);
        } else if (new Validator<>(Customer.class).validUniqueId(id)) {
            req.setAttribute("error", " ID " + id + " duplicates an existing one ! \n ");
            req.setAttribute("error2", "\n  Enter another ID ");
            doGet(req, resp);
        } else {
            service.create(new Customer(Long.valueOf(id), name, city, Long.valueOf(budget)));
            addViewImpl.updatePostDo(req, resp);
        }
    }
}
