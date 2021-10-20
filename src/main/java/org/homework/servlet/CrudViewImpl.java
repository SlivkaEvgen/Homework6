package org.homework.servlet;

import lombok.SneakyThrows;
import org.homework.model.BaseModel;
import org.homework.service.CrudService;
import org.homework.service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class CrudViewImpl<T extends BaseModel<ID>, ID> extends HttpServlet implements CrudView<T, ID> {

    private final Class<T> classModel;
    private final CrudService<T, ID> service;
    private T t;

    public CrudViewImpl(Class<T> classModel) {
        this.classModel = classModel;
        service = ServiceFactory.of(classModel);
    }

    @SneakyThrows
    @Override
    public void addNewDoGet(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getRequestURI().equalsIgnoreCase("/all/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/")) {
            getAll(req, resp);
        }
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/addNew" + classModel.getSimpleName() + "View.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getRequestURI().equalsIgnoreCase("/all/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/")) {
            getAll(req, resp);
        }
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/addNew" + classModel.getSimpleName() + "View.jsp")
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    public void getAll(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("list", service.findAll());
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/" + classModel.getSimpleName().toLowerCase(Locale.ROOT)
                        + "/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "ListView.jsp")
                .forward(req, resp);
        resp.sendRedirect(req.getRequestURI() + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/all");
    }

    @SneakyThrows
    @Override
    public void updateGetDo(HttpServletRequest req, HttpServletResponse resp) {
        Optional<T> optionalT = service.findById((ID) req.getParameter("id"));
        if (optionalT.isPresent()) {
            this.t = optionalT.get();
            req.setAttribute(classModel.getSimpleName().toLowerCase(Locale.ROOT) + "", this.t);
            req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) +
                            "/update" + classModel.getSimpleName() + "View.jsp")
                    .forward(req, resp);
        }
    }

    @SneakyThrows
    @Override
    public void updatePostDo(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(classModel.getSimpleName().toLowerCase(Locale.ROOT) + "", this.t);
        resp.sendRedirect("/all/" + classModel.getSimpleName().toLowerCase(Locale.ROOT) + "/");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        service.deleteById((ID) req.getParameter("id"));
        getAll(req,resp);
    }
}
