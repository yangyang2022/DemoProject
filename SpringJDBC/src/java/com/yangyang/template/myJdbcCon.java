package com.yangyang.template;

public class myJdbcCon {

    private void openConnection(){
        System.out.println("open connection ...");
    }

    private void closeConnect(){
        System.out.println("close connect ... ");
    }

    /**
     * 将要实现的方法 创建在 模板中 ...
     * 传入一个钩子函数的接口
     */
    public void add(int id){
        execute(new myCallBack() {
            @Override
            public void doIntemplate() {
                System.out.println("add a "+id);
            }
        });
    }
    public void delete(int id){
        execute(new myCallBack() {
            @Override
            public void doIntemplate() {
                System.out.println("delete a "+id);
            }
        });
    }

    public void execute(myCallBack callBack){
        openConnection();
        callBack.doIntemplate();
        closeConnect();
    }
}
