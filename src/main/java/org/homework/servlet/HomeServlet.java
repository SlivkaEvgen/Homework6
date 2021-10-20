package org.homework.servlet;

import lombok.SneakyThrows;
import org.homework.model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/all/*"})
public class HomeServlet extends HttpServlet {

    private static HomeServlet homeServlet;

    public static HomeServlet getInstance() {
        if (homeServlet == null) {
            homeServlet = new HomeServlet();
        }
        return homeServlet;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getRequestURI().equalsIgnoreCase("/all/skill/")) {
            new CrudViewImpl<>(Skill.class).doGet(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/company/")) {
            new CrudViewImpl<>(Company.class).doGet(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/customer/")) {
            new CrudViewImpl<>(Customer.class).doGet(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/project/")) {
            new CrudViewImpl<>(Project.class).doGet(req, resp);
        }
        if (req.getRequestURI().equalsIgnoreCase("/all/developer/")) {
            new CrudViewImpl<>(Developer.class).doGet(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
