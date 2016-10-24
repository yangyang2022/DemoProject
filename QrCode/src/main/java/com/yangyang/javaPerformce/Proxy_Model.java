package com.yangyang.javaPerformce;

interface IDBQuery{
    String request();
}
class DBQuery implements IDBQuery{

    public DBQuery(){
        SleepUtils.sleepSeconds(3);
    }

    @Override
    public String request() {
        return "request string ";
    }
}
class DBQueryProxy implements IDBQuery{
    private DBQuery real = null;
    @Override
    public String request() {
        if(real == null) real = new DBQuery();
        return real.request();
    }
}
public class Proxy_Model {

    public static void main(String[] args) {
        IDBQuery query = new DBQueryProxy();
        query.request();
    }
}
