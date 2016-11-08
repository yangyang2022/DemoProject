package com.yangyang.beanScanner;

import com.yangyang.annotation.SocketCmd;
import com.yangyang.annotation.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//scanner
@Component
public class Scanner implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clz = bean.getClass();
        Class<?>[] interfaces = clz.getInterfaces();

        if(interfaces != null && interfaces.length > 0){

            for(Class<?> interf : interfaces){
                SocketModule socketModule = interf.getAnnotation(SocketModule.class);
                if(socketModule == null) continue;
                Method[] methods = interf.getMethods();
                if(methods != null && methods.length > 0){
                    for(Method method:methods){
                        SocketCmd socketCmd = method.getAnnotation(SocketCmd.class);
                        if(socketCmd == null) continue;
                        short module = socketModule.module();
                        short cmd = socketCmd.cmd();

                        Invoker invoker = Invoker.invokeOf(bean,method);

                        if(InvokeHolder.getInvoker(module,cmd) == null){
                            //add
                            InvokeHolder.addInvoker(module,cmd,invoker);
                        }else {
                            System.out.println("complicated register actuator ...");
                        }
                    }
                }
            }

        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
