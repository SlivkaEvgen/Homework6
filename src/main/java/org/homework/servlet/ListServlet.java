package org.homework.servlet;

import lombok.SneakyThrows;
import org.homework.model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/all/*"})
public class ListServlet extends HttpServlet {

    private final CrudView<Skill, Long> getListView = new CrudViewImpl<>(Skill.class);
    private final CrudView<Company, Long> getListViewCom = new CrudViewImpl<>(Company.class);
    private final CrudView<Customer, Long> getListViewCus = new CrudViewImpl<>(Customer.class);
    private final CrudView<Project, Long> getListViewPro = new CrudViewImpl<>(Project.class);
    private final CrudView<Developer, Long> getListViewDev = new CrudViewImpl<>(Developer.class);
    private static ListServlet listServlet;

    public static ListServlet getInstance() {
        if (listServlet == null) {
            listServlet = new ListServlet();
        }
        return listServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getRequestURI().equalsIgnoreCase("/all/skill/")) {
            getListView.getAll(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/company/")) {
            getListViewCom.getAll(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/customer/")) {
            getListViewCus.getAll(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/project/")) {
            getListViewPro.getAll(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/developer/")) {
            getListViewDev.getAll(req, resp);
        }
    }
}