import com.sun.mail.util.MailSSLSocketFactory;
import com.yangyang.utils.MailUtil;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class TestMail {
    //授权吗 gdetbggcpkdnhijd
    private String fromMeEmail = "yangyang20666@qq.com";
    private String toYouEmail = "yangyanghdu@qq.com";
    private String toYouGmail = "yangyang20222@gmail.com";
    private String username = "yangyang20666";
    private String checkPassword = "gdetbggcpkdnhijd";
    @Test
    public void testMail1() {
        Transport transport = null;
        try {
            //1: 创建session(设置prop和session)
            Properties props = new Properties();
            //设置邮件协议
            props.setProperty("mail.transport.protocol","smtp");
            //设置邮件发送的服务器
            props.setProperty("mail.host","smtp.qq.com");
            //设置发送服务器验证
            props.setProperty("mail.smtp.auth","true");

            //开ssl加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props);
            //打开邮件调试,可以看到邮件发送过程
            session.setDebug(true);

            //2: 创建message

            Message message = new MimeMessage(session);
            message.setSubject("大家来看看,我通过Java发送邮件");
            message.setText("这是邮件的内容,通过java 发送垃圾邮件");

            //设置邮件从什么地发的发送的
            message.setFrom(new InternetAddress("yangyang20666@qq.com"));

            //设置邮件收件人
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("yangyanghdu@qq.com"));
            //设置抄送 (复制一份)
            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("yangyang20222@gmail.com"));

            //3: 通过transport对象完成邮件发送
            transport = session.getTransport();
            //用户名和密码
            transport.connect("yangyang20666","gdetbggcpkdnhijd");
            transport.sendMessage(message,new InternetAddress[]{new InternetAddress("yangyanghdu@qq.com")});
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if(transport != null) transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testMail2() {
        try {

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

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMeEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(toYouEmail));

            message.setSubject("一封邮件");
            message.setContent("<h1 style='color:red'>这是一个有颜色的内容</h1>","text/html;charset=utf-8");

            //需要将用户名和密码设置到session
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMail3() {
        //发送带附件的邮件MulitiPart上传附件

        try {
            Session session = MailUtil.getSession();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMeEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(toYouEmail));

            message.setSubject("带有附件的javaMail");
            //message.setContent("<h1 style='color:red'>这是一个有颜色的内容</h1>","text/html;charset=utf-8");

            //整个邮件的multipart(假设有两个附件则需要三个bodyPart,两个放附件一个放邮件正文)
            Multipart emailPart = new MimeMultipart();

            //附件1
            MimeBodyPart att1 = new MimeBodyPart();
            att1.setDataHandler(new DataHandler(new FileDataSource("C:\\upload\\2016-08-24T23-06-49.57.jpg")));
            att1.setFileName("shifu.jpg");

            //附件2
            MimeBodyPart att2 = new MimeBodyPart();
            att2.setDataHandler(new DataHandler(new FileDataSource("C:\\upload\\2016-08-24T23-07-27.376.exe")));
            //设置中文编码
            att2.setFileName(MimeUtility.encodeText("破解.exe"));

            //邮件正文(也有可能有图片,so 也需要一个multipart)
            MimeBodyPart content = new MimeBodyPart();

            //创建了正文中的图片
            Multipart contentMulitpart = new MimeMultipart();
            MimeBodyPart imgBodyPart = new MimeBodyPart();
            imgBodyPart.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\syy\\Desktop\\UUU_VPN\\g1.jpg")));
            //为这个图片设置一个id 通过cid:#id
            imgBodyPart.setContentID("smile");

            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent("<h1>这是一个带有附件的图片</h1><img src='cid:smile' />","text/html;charset=utf-8");

            contentMulitpart.addBodyPart(imgBodyPart);
            contentMulitpart.addBodyPart(htmlBodyPart);

            //完成了邮件正文的设置
            content.setContent(contentMulitpart);

            //设置邮件的信息
            emailPart.addBodyPart(att1);
            emailPart.addBodyPart(att2);
            emailPart.addBodyPart(content);

            //设置到信息里面
            message.setContent(emailPart);

            //需要将用户名和密码设置到session
            Transport.send(message);


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
