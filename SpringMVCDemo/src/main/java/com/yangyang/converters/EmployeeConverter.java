package com.yangyang.converters;

import com.yangyang.dao.DepartmentDao;
import com.yangyang.model.Department;
import com.yangyang.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmployeeConverter implements Converter<String,Employee>{

    @Resource
    private DepartmentDao departmentDao;
    @Override
    public Employee convert(String s) {
        if(s!=null){
            String[] vals = s.split("-");
            //小莫-momo@qq.com-男-103
            if(vals != null && vals.length ==4){
                String lastName = vals[0];
                String email = vals[1];
                String gender = vals[2];
                Department dep = departmentDao.getDepByID(Integer.parseInt(vals[3]));
                Employee e = new Employee(null,lastName,email,gender,dep);

                System.out.println(s +" --> "+e.getLastName());
                
                return e;
            }
        }
        return null;
    }
}
