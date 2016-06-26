package com.yangyang.dao;

import com.yangyang.Utils.XMLUtil;
import com.yangyang.model.EmpException;
import com.yangyang.model.Dep;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class DepDao {
    private Document dd;
    private Document ed;

    private static final String fileName = "/xml/deps.xml";

    public DepDao() {
        dd = XMLUtil.getDepDocuement();
        ed = XMLUtil.getEmpDocuemnet();
    }
    private void write(){
        XMLUtil.write2XML(dd,fileName);
    }

    public int getDepEmpNums(int depId){
        String path = "/emps/emp[depId="+depId+"]";
        return ed.selectNodes(path).size();
    }
    private Element loadDepElementByID(int id){
        String xpath = "/deps/dep[id="+id+"]";
        Element e = (Element) dd.selectSingleNode(xpath);
        return e;
    }
    private Dep element2Dep(Element e){
        Dep dep = new Dep();
        dep.setId(Integer.parseInt(e.elementText("id")));
        dep.setDepName(e.elementText("depName"));
        return dep;
    }

    public int add(Dep dep){
        if(dep.getDepName() == null || "".equals(dep.getDepName()))
            throw new EmpException(" 部门名称 不能为空!");
        if(load(dep.getId()) != null)
            throw new EmpException(" 添加的部门已经存在! ");

        Element e = dd.getRootElement().addElement("dep");
        int id = XMLUtil.getMaxId(dd,"/deps/dep/id");
        e.addElement("id").setText((id+1)+"");
        e.addElement("depName").setText(dep.getDepName());
        write();
        return id;
    }
    public void delete(int id){

        if(getDepEmpNums(id) > 0 ) throw new EmpException("要删除的部门还有员工!");

        Element root = dd.getRootElement();
        Element e = loadDepElementByID(id);
        if(e == null )throw new EmpException("要删除的部门不存在!");
        root.remove(e);
        write();
    }
    public void update(Dep dep){
        Element e = loadDepElementByID(dep.getId());
        if(e == null ) throw new EmpException("要更新的用户不存在!");
        e.element("depName").setText(dep.getDepName());
        write();
    }

    public Dep load(int id){
        Element e = loadDepElementByID(id);
        if (e == null ) return null;
        return element2Dep(e);
    }
    public List<Dep> list(){
        String xpath = "/deps/dep";
        List<Element> eles = dd.selectNodes(xpath);
        List<Dep> deps = new ArrayList<>();
        for(Element e : eles){
            deps.add(element2Dep(e));
        }
        return deps;
    }
}
