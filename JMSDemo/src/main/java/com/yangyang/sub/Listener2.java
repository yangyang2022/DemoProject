package com.yangyang.sub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听,需要在consumer里面注册消息监听
 */
public class Listener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者2 --> getMessgae: "+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
