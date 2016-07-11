package com.yangyang.sub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者1 --> getMessgae: "+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
