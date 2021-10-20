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

@WebServlet(urlPatterns = {"/customer/customer/*"})
public class UpdateCustomerServlet extends HttpServlet {

    private final CrudService<Customer, Long> service = ServiceFactory.of(Customer.class);
    private final CrudView<Customer, Long> updateDoGet = new CrudViewImpl<>(Customer.class);
    private static UpdateCustomerServlet updateCustomerServlet;

    public static UpdateCustomerServlet getInstance() {
        if (updateCustomerServlet == null) {
            updateCustomerServlet = new UpdateCustomerServlet();
        }
        return updateCustomerServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        updateDoGet.updateGetDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String budget = req.getParameter("budget");

        if (!Validator.validName(name)) {
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
        }else {
            service.update(new Customer(Long.valueOf(req.getParameter("id")), name, city, Long.valueOf(budget)));
            updateDoGet.updatePostDo(req, resp);
        }
    }
}

