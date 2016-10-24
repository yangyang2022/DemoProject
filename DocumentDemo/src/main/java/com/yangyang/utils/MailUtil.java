package com.yangyang.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

    //单利session只能有一个人发送邮件
    public static Session getSession() throws GeneralSecurityException {

        String fromMeEmail = "yangyang20666@qq.com";
        String toYouEmail = "yangyanghdu@qq.com";
        String toYouGmail = "yangyang20222@gmail.com";
        String username = "yangyang20666";
        String checkPassword = "gdetbggcpkdnhijd";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.host","smtp.qq.com");
        props.setProperty("mail.smtp.auth","true");
        //开ssl加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,checkPassword);
            }
        });

        session.setDebug(true);

        return session;
    }
}
