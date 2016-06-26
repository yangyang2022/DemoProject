package com.yangyang.web.reflect;

import com.yangyang.web.Util.PropUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFctory implements IFactoryDao{
    private static PropertiesFctory factory = new PropertiesFctory();

    private Map<String,Object> daos = new HashMap<>();

    private PropertiesFctory(){}

    public static IFactoryDao getInstance(){
        return factory;
    }
    @Override
    public Object getDao(String name)  {

        if(daos.containsKey(name)) return daos.get(name);

        Properties prop = PropUtils.getDaoProperties();
        String cn = prop.getProperty(name);
        Object obj = null;
        try {
             obj= Class.forName(cn).newInstance();
             daos.put(name,obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
