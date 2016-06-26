package com.yangyang.web;

import com.yangyang.Utils.RequestUtils;
import com.yangyang.dao.IAddressDao;
import com.yangyang.dao.IUserDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Address;
import com.yangyang.model.Pager;
import com.yangyang.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddressServlet",urlPatterns = "/address")
public class AddressServlet extends com.yangyang.web.BaseServlet {
    private IUserDao userDao;
    private IAddressDao addressDao;

    @ShopDI
    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @ShopDI
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String list(HttpServletRequest request, HttpServletResponse response){
        Pager<User> users = userDao.find("");
        request.setAttribute("users",users);
        return "user_id";
    }
    @com.yangyang.web.Auth
    public String add(HttpServletRequest request, HttpServletResponse response){
        Address address = (Address) RequestUtils.setParams(Address.class,request);
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = userDao.load(user_id);
        address.setUser(user);

        if(!RequestUtils.validate(Address.class,request)){
            request.setAttribute("user",user);
            return "address/addInput.jsp";
        }
        addressDao.add(address,user_id);
        return redirect+"user?method=show&id="+user_id;
    }
    @com.yangyang.web.Auth
    public String delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        addressDao.delete(id);
        return redirect+"user?method=show&id="+user_id;
    }
    @com.yangyang.web.Auth
    public String addInput(HttpServletRequest request, HttpServletResponse response){
        User user = userDao.load(Integer.parseInt(request.getParameter("user_id")));
        request.setAttribute("user",user);
        return "address/addInput.jsp";
    }
    @com.yangyang.web.Auth
    public String updateInput(HttpServletRequest request, HttpServletResponse response){
        Address address = addressDao.load(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("address",address);
        return "address/updateInput.jsp";
    }
    @com.yangyang.web.Auth
    public String update(HttpServletRequest request, HttpServletResponse response){
        Address address = addressDao.load(Integer.parseInt(request.getParameter("id")));
        Address ta = (Address) RequestUtils.setParams(Address.class,request);

        address.setName(ta.getName());
        address.setPostcode(ta.getPostcode());
        address.setPhone(ta.getPhone());

        if(!RequestUtils.validate(Address.class,request)){
            request.setAttribute("address",address);
            return "address/updateInput.jsp";
        }
        addressDao.update(address);
        return redirect+"user?method=show&id="+address.getUser().getId();
    }
}
