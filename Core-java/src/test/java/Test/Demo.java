package Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Base{
    public Base(){
        System.out.println("Base ... ");
        Method[] ms = this.getClass().getDeclaredMethods();
        for(Method m:ms){
            String name = m.getName();
            if(name.startsWith("set")){
                name = name.substring(3);
                name = name.substring(0,1).toLowerCase()+name.substring(1);
                try {
                    m.invoke(this,"yangyang");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class Demo extends Base{
    private String username;
    static {
        System.out.println("Demo1... ");
    }
    {
        System.out.println("Demo2 ... ");
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void main(String[] args) {
        System.out.println(new Demo().getUsername());
    }
}
