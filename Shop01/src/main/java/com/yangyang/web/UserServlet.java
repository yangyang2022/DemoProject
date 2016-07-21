package com.yangyang.web;

import com.yangyang.Utils.RequestUtils;import com.yangyang.model.IUserDao;import com.yangyang.model.ShopDI;import com.yangyang.model.Pager;import com.yangyang.model.ShopException;
import com.yangyang.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    private static final String USER_LIST = "user/list.jsp";
    private static final String REDIRECT_USER_LIST = redirect+"user?method=list";
    private IUserDao userDao;

    @ShopDI
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String list(HttpServletRequest request, HttpServletResponse response){
        Pager<User> users = userDao.find("");
        request.setAttribute("users",users);
        return USER_LIST;
    }

    public String addInput(HttpServletRequest request, HttpServletResponse response){
        return "user/addInput.jsp";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete(id);
        return redirect+"user?method=list";
    }
    public String updateInput(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        User u = userDao.load(id);
        request.setAttribute("user",u);
        return "user/updateInput.jsp";
    }
    @Auth
    public String updateSelfInput(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("user",(User)request.getSession().getAttribute("loginUser"));
        return "user/updateSelfInput.jsp";
    }
    public String updateSelf(HttpServletRequest request, HttpServletResponse response){
        User tu = (User) RequestUtils.setParams(User.class,request);
        User u = (User) request.getSession().getAttribute("loginUser");

        u.setNickname(tu.getNickname());
        u.setPassword(tu.getPassword());
        //
        //boolean validate = RequestUtils.validate(User.class,request);
        //if(!validate){
        //    request.setAttribute("user",u);
        //    return "user/updateSelfInput.jsp";
        //}

        userDao.update(u);
        return redirect+"goods?method=list";
    }
    public String update(HttpServletRequest request, HttpServletResponse response){
        User tu = (User) RequestUtils.setParams(User.class,request);
        int id = Integer.parseInt(request.getParameter("id"));
        User u = userDao.load(id);

        u.setNickname(tu.getNickname());
        u.setPassword(tu.getPassword());

        boolean validate = RequestUtils.validate(User.class,request);
        if(!validate){
            request.setAttribute("user",u);
            return "user/updateInput.jsp";
        }

        userDao.update(u);
        return REDIRECT_USER_LIST;
    }
    public String changeType(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        User u = userDao.load(id);
        if(u.getType() == 0 ){
            u.setType(1);
        }else {
            u.setType(0);
        }
        userDao.update(u);
        return REDIRECT_USER_LIST;
    }

    public String add(HttpServletRequest request, HttpServletResponse response){
        User u = (User) RequestUtils.setParams(User.class ,request);
        boolean validate = RequestUtils.validate(User.class,request);
        if(!validate){
            return "user/addInput.jsp";
        }
        try {
            userDao.add(u);
        } catch (ShopException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "inc/error.jsp";
        }
        return redirect+"user?method=list";
    }

    @Auth("any")
    public String loginInput(HttpServletRequest request, HttpServletResponse response){
        return "user/loginInput.jsp";
    }
    @Auth("any")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        return "user/loginInput.jsp";
    }
    @Auth("any")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User loginUser = userDao.login(username,password);
            request.getSession().setAttribute("loginUser",loginUser);

        } catch (ShopException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "/inc/error.jsp";
        }
        //跳转到goods烈面
        return redirect+"product?method=list";
    }
    @Auth
    public String show(HttpServletRequest request, HttpServletResponse response){
        User user = userDao.load(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user",user);
        return "user/show.jsp";
    }
}
