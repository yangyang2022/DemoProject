package com.yangyang.web;

import com.yangyang.Utils.RequestUtils;
import com.yangyang.dao.ICategoryDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Category;
import com.yangyang.model.ShopException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    private ICategoryDao categoryDao;

    @ShopDI
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public String list(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("good_name");
        List<Category> list = categoryDao.list(name);
        request.setAttribute("cs",list);
        return "category/list.jsp";
    }
    public String addInput(HttpServletRequest request, HttpServletResponse response){
        return "category/addInput.jsp";
    }
    public String add(HttpServletRequest request, HttpServletResponse response){
        Category category = (Category) RequestUtils.setParams(Category.class,request);

        if(!RequestUtils.validate(Category.class,request)){
            return "category/addInput.jsp";
        }
        categoryDao.add(category);
        return redirect+"/category";
    }

    public String show(HttpServletRequest request, HttpServletResponse response){
        Category category = categoryDao.load(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("category",category);

        return "category/show.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            categoryDao.delete(id);
        } catch (ShopException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "/inc/error.jsp";
        }
        return redirect+"/category";
    }
    public String updateInput(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Category category  = categoryDao.load(id);
        request.setAttribute("category",category);
        return "category/updateInput.jsp";
    }
    public String update(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Category oldCategory  = categoryDao.load(id);

        Category category = (Category) RequestUtils.setParams(Category.class,request);
        if(!RequestUtils.validate(Category.class,request)){
            request.setAttribute("category",category);
            return "category/updateInput.jsp";
        }
        oldCategory.setName(category.getName());
        categoryDao.update(oldCategory);

        return redirect+"/category";
    }
}
