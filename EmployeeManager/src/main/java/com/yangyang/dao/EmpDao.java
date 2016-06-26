package com.yangyang.dao;

import com.yangyang.Utils.XMLUtil;
import com.yangyang.model.Dep;
import com.yangyang.model.Emp;
import com.yangyang.model.EmpException;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class EmpDao {
    private static final String EMP_XML = "/xml/emps.xml";
    private Document ed;
    private DepDao depDao;

    public  EmpDao(){
        ed = XMLUtil.getEmpDocuemnet();
        depDao = new DepDao();
    }
    private void write(){XMLUtil.write2XML(ed,EMP_XML);}

    private Element loadById(int id){
        String xpath = "/emps/emp[id='"+id+"']";
        return (Element) ed.selectSingleNode(xpath);
    }
    //根据 员工对象 和 部门id 添加对象
    public void add(Emp emp,int depId){
        Dep dep = depDao.load(depId);
        if(dep == null ) throw new EmpException("要添加员工的部门不存在!");

        Element e = ed.getRootElement().addElement("emp");
        e.addElement("id").setText((XMLUtil.getMaxId(ed,"/emps/emp/id")+1) + "");
        e.addElement("name").setText(emp.getUsername());
        e.addElement("sex").setText(emp.getSex());
        e.addElement("salary").setText(emp.getSalary()+"");
        e.addElement("depId").setText(depId+"");
        write();
    }

    private Element checkEmpIsValide(int id,String errorMsg){
        Element e = loadById(id);
        if(e == null ) throw new EmpException(errorMsg);
        return e;
    }
    private Emp element2Emp(Element e){
        Emp emp = new Emp();
        emp.setId(Integer.parseInt(e.elementText("id")));
        emp.setUsername(e.elementText("name"));
        emp.setSalary(Double.valueOf(e.elementText("salary")));
        emp.setDepId(Integer.parseInt(e.elementText("depId")));
        emp.setSex(e.elementText("sex"));
        return emp;
    }
    public void delete(int id){
        ed.getRootElement().remove(checkEmpIsValide(id,"要删除的用户不存在!"));
        write();
    }
    public Emp load(int id){
        return element2Emp(checkEmpIsValide(id,"要查找的用户不存在!"));
    }
    public void update(Emp emp,int depId){
        Element e = checkEmpIsValide(emp.getId(),"更新的部门不存在");
        //id can't change
        e.element("name").setText(emp.getUsername());
        e.element("sex").setText(emp.getSex());
        e.element("salary").setText(emp.getSalary()+"");
        e.element("depId").setText(depId+"");
        write();
    }
    public List<Emp> list(){
        String xpath = "/emps/emp";
        List<Element> elems = ed.selectNodes(xpath);
        if(elems == null || elems.size() == 0) return null;
        List<Emp> emps = new ArrayList<>();
        for(Element e:elems){
            emps.add(element2Emp(e));
        }
        return emps;
    }
    public List<Emp> list(int depId){
        return list(depId,"");
    }
    public List<Emp> list(String name){
        return list(-1,name);
    }
    public List<Emp> list(int depId,String name){
        if(depId == 0 && "".equals(name.trim())){
            return list();
        }
        List<Emp> emps = new ArrayList<>();
        String path = "/emps/emp[";
        if(depId > 0 ) {
            path+="depId="+depId;
            if(name != null && !"".equals(name.trim())){
                path += " and contains(name,'"+name+"')";
            }
        }else{ // 没有选择 depId = -1
            if(name != null && !"".equals(name.trim()))
                path += "contains(name,'"+name+"')";
        }
        path += "]";
        //System.out.println("path: "+path);

        List<Element> eles = ed.selectNodes(path);
        for(Element e:eles){
            emps.add(element2Emp(e));
        }
        return emps;
    }
}
