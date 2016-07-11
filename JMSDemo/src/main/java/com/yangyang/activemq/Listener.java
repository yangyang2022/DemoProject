package com.yangyang.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听,需要在consumer里面注册消息监听
 */
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("getMessgae: "+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
