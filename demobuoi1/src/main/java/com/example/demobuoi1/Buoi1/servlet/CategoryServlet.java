package com.example.demobuoi1.Buoi1.servlet;

import com.example.demobuoi1.Buoi1.entity.Category;
import com.example.demobuoi1.Buoi1.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "CategoryServlet", value = {
        "/category/hien-thi", //get
        "/category/detail", //get
        "/category/delete", //get
        "/category/view-update", //get
        "/category/update", //post
        "/category/view-add",   //get
        "/category/add",    //post
        "/category/search"  //get
})
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiDuLieu(request, response);
        } else if (uri.contains("detail")) {
            this.detailDuLieu(request, response);
        } else if (uri.contains("delete")) {
            this.deleteDulieu(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateDuLieu(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddDuLieu(request, response);
        } else if (uri.contains("search")) {
            this.searchDuLieu(request, response);
        }

    }

    private void searchDuLieu(HttpServletRequest request, HttpServletResponse response) {
    }

    private void viewAddDuLieu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/buoi1/add-category.jsp").forward(request, response);
    }

    private void viewUpdateDuLieu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = categoryService.getOne(Long.valueOf(id));
        request.setAttribute("cate", category);
        request.getRequestDispatcher("/buoi1/update-cate.jsp").forward(request, response);
    }

    private void deleteDulieu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Category category = categoryService.getOne(Long.valueOf(id));
        categoryService.delete(category);
        response.sendRedirect("/category/hien-thi");
    }

    private void detailDuLieu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = categoryService.getOne(Long.valueOf(id));
        request.setAttribute("cate", category);
        request.getRequestDispatcher("/buoi1/detail-cate.jsp").forward(request, response);
    }

    private void hienthiDuLieu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", categoryService.getAll());
        request.getRequestDispatcher("/buoi1/categorys.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("update")) {
            this.updateDuLieu(request, response);
        } else if (uri.contains("add")) {
            this.addDuLieu(request, response);
        }

    }

    private void addDuLieu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        Category category = new Category();

        BeanUtils.populate(category, request.getParameterMap());

        categoryService.add(category);

        response.sendRedirect("/category/hien-thi");
    }

    private void updateDuLieu(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {

        String id = request.getParameter("id");

        Category category = categoryService.getOne(Long.valueOf(id));

        BeanUtils.populate(category, request.getParameterMap());

        categoryService.update(category);

        response.sendRedirect("/category/hien-thi");
    }
}
