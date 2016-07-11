package com.yangyang.sub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息订阅者1
 *
 */
public class JmsConsumer {
    private static final String UESRNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        ConnectionFactory connectionFactory = null;//链接工厂
        Connection connection = null;
        Session session = null;//接受发送的一个线程
        Destination destination = null;//消息的目的地
        MessageConsumer messageConsumer = null;

        connectionFactory = new ActiveMQConnectionFactory(UESRNAME,PASSWORD,BROKEURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //destination = session.createQueue("FirstQueue");//创建消息队列
            destination = session.createTopic("FirstQueue");//创建消息队列

            messageConsumer = session.createConsumer(destination);

            //注册消息监听
            messageConsumer.setMessageListener(new Listener());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
