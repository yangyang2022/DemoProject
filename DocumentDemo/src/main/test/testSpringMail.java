import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-hibernate.xml")
public class testSpringMail {
    @Resource
    private JavaMailSender mailSender;

    //spring 发送email
    @Test
    public void testSpringMail() {

        //创建message
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setFrom("yangyang20666@qq.com");
            helper.setTo("yangyanghdu@qq.com");

            helper.setSubject("通过spring 发送的邮件");
            helper.setText("<div style='color:red'>通过spring发送邮件</div><img src='cid:shifu' />",true);

            FileSystemResource fsr = new FileSystemResource(new File("C:\\upload\\2016-08-24T23-06-49.57.jpg"));
            FileSystemResource fsr2 = new FileSystemResource(new File("C:\\Users\\syy\\Desktop\\UUU_VPN\\g1.jpg"));

            helper.addAttachment(MimeUtility.encodeText("师傅图像1.jpg"),fsr);

            //添加邮件类容里面的图片
            helper.addInline("shifu",fsr2);

            //发送邮件
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
