package com.yangyang.template;

/**
 * 基于继承实现模板模式
 */
public abstract class myJdbcByInheri {
    private void openConnection(){
        System.out.println("open connection ...");
    }

    private void closeConnect(){
        System.out.println("close connect ... ");
    }

    public abstract void run();

    public void execute(){
        openConnection();
        run();
        closeConnect();
    }
}
