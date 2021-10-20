package org.homework.servlet;

import org.homework.model.BaseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CrudView<T extends BaseModel<ID>, ID> {

    void addNewDoGet(HttpServletRequest req, HttpServletResponse resp);

    void delete(HttpServletRequest req, HttpServletResponse resp);

    void getAll(HttpServletRequest req, HttpServletResponse resp);

    void updateGetDo(HttpServletRequest req, HttpServletResponse resp);

    void updatePostDo(HttpServletRequest req, HttpServletResponse resp);
}
