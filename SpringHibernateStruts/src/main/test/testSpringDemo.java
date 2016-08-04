import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class testSpringDemo {
    
    public void sayHello(){
        System.out.println("say --> hello world");
    }
    
    @Test
    public void testDemo() throws IOException, IllegalAccessException, InstantiationException {
        //String s = "";
        //boolean ss = StringUtils.isEmpty(s);
        //System.out.println(ss);
        //
        //System.out.println("--> "+ClassUtils.getShortName(testSpringDemo.class));
        //System.out.println(ClassUtils.forName());
        //System.out.println(DigestUtils.md5Digest("yangyang".getBytes()));

        //FileCopyUtils.copy(new File("C:\\kms8.log"),new File("C:\\kms8_copy.log"));

        //Field f = ReflectionUtils.findField(User.class,"username");
        //MyTestBean bean = BeanUtils.instantiate(MyTestBean.class);
        //System.out.println(bean.getStr());
        //MyTestBean bean2 = MyTestBean.class.newInstance();
        //System.out.println(bean2.getStr());
        List<String> aliais = Arrays.asList("shen","yang","yang","hello","world");
        String[] strs = StringUtils.toStringArray(aliais);
        for(String s: strs){
            System.out.println(s);
        }
    }
}
