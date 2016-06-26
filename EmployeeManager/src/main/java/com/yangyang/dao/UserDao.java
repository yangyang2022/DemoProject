package com.yangyang.dao;

import com.yangyang.Utils.XMLUtil;
import com.yangyang.model.EmpException;
import com.yangyang.model.User;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Document ud;
    private static final String fileName = "/xml/users.xml";

    public UserDao() {
        ud = XMLUtil.getUserDocument();
    }
    private void writeUser(){
        XMLUtil.write2XML(ud,fileName);
    }

    private Element loadElementByUsername(String username){
        String xpath = "/users/user[username='"+username+"']";
        Element e = (Element) ud.selectSingleNode(xpath);
        return e;
    }
    public void add(User user){
        if(user.getUsername() == null || "".equals(user.getUsername()))
            throw new EmpException("用户名不能为空!");
        if(load(user.getUsername()) != null)
            throw new EmpException("用户已存在,不能添加!");

        Element e = ud.getRootElement().addElement("user");
        e.addElement("username").addText(user.getUsername());
        e.addElement("password").addText(user.getPassword());
        e.addElement("nickname").addText(user.getNickname());
        writeUser();
    }
    public void delete(String username){
        Element root = ud.getRootElement();
        root.remove(loadElementByUsername(username));
        writeUser();
    }
    public void update(User user){
        Element e = loadElementByUsername(user.getUsername());

        if(e == null ) throw new EmpException("要更新的用户不存在!");
        e.element("password").setText(user.getPassword());
        e.element("nickname").setText(user.getNickname());
        writeUser();
    }
    public User load(String username){
        Element e = loadElementByUsername(username);
        if(e == null) return null;
        return Element2User(e);
    }
    private User Element2User(Element e){
        User user = new User();
        user.setUsername(e.elementText("username"));
        user.setPassword(e.elementText("password"));
        user.setNickname(e.elementText("nickname"));
        return user;
    }
    public List<User> list(){
        String xpath = "/users/user";
        List<Element> eles = ud.selectNodes(xpath);
        List<User> users = new ArrayList<>();
        for(Element e:eles){
            users.add(Element2User(e));
        }
        return users;
    }
    public User login(String username,String password){
        User user = load(username);
        if(user == null){
            throw new EmpException("用户不存在!");
        }else if(!user.getPassword().equals(password)){
            throw new EmpException("用户密码不正确");
        }
        return user;
    }
}
