package org.homework.servlet;

import org.homework.model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete/*"})
public class DeleteServlet extends HomeServlet {

    private final CrudView<Skill, Long> deleteView = new CrudViewImpl<>(Skill.class);
    private final CrudView<Project, Long> deleteViewPro = new CrudViewImpl<>(Project.class);
    private final CrudView<Company, Long> deleteViewCom = new CrudViewImpl<>(Company.class);
    private final CrudView<Developer, Long> deleteViewDev = new CrudViewImpl<>(Developer.class);
    private final CrudView<Customer, Long> deleteViewCus = new CrudViewImpl<>(Customer.class);
    private static DeleteServlet deleteServlet;

    public static DeleteServlet getInstance() {
        if (deleteServlet == null) {
            deleteServlet = new DeleteServlet();
        }
        return deleteServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        if (req.getRequestURI().equalsIgnoreCase("/delete/skill")) {
            deleteView.delete(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/delete/company")) {
            deleteViewCom.delete(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/delete/customer")) {
            deleteViewCus.delete(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/delete/project")) {
            deleteViewPro.delete(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/delete/developer")) {
            deleteViewDev.delete(req, resp);
        }
    }
}