package com.yangyang.web;

import com.yangyang.Utils.RequestUtils;
import com.yangyang.dao.ICategoryDao;
import com.yangyang.dao.IProductDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "ProductServlet",urlPatterns = "/product")
public class ProductServlet extends BaseServlet {
    private ICategoryDao categoryDao;
    private IProductDao productDao;

    @ShopDI
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    @ShopDI
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Auth("any")
    public String list(HttpServletRequest request, HttpServletResponse response){

        return "product/list.jsp";
    }
    public String addInput(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("cs",categoryDao.list());
        return "product/addInput.jsp";
    }
    public String add(HttpServletRequest request, HttpServletResponse response){
        Product p = (Product) RequestUtils.setParams(Product.class,request);
        if(!RequestUtils.validate(Product.class,request)){
            addInput(request, response);
            return "product/addInput.jsp";
        }
        int cid = 0;
        try {
            cid = Integer.parseInt(request.getParameter("cid"));
        } catch (NumberFormatException e) {
            ((Map)request.getAttribute("errors")).put("cid","商品类别必须选择!");
            addInput(request, response);
            return "product/addInput.jsp";
        }
        return redirect+"/product";
    }

}
