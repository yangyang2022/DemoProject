package com.yangyang.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {

    private static final String UESRNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final int SENDNUM = 10; //发送的消息数量

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;//链接工厂
        Connection connection = null;
        Session session = null;//接受发送的一个线程
        Destination destination = null;//消息的目的地
        MessageProducer messageProducer = null;//消息发送者

        //1:实例化连接工长
        connectionFactory = new ActiveMQConnectionFactory(UESRNAME,PASSWORD,BROKEURL);

        //2: 通过工厂获取 connection
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // 参数1:是否添加事务 2: 会话自动确认客户收到的消息
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("FirstQueue");//创建消息队列

            messageProducer = session.createProducer(destination);//创建消息生产者

            sendMessage(session,messageProducer);

            session.commit();//提交事务

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
        for (int i = 0; i < SENDNUM; ++i) {
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息: "+i);
            System.out.println("send success !");
            messageProducer.send(message);
        }
    }
}
