import org.junit.Test;

@Hello
class Demo{}
@Hello
class Demo2{}
class Demo3{}

public class TestDemo {
    @Test
    public void testDemo1() {
        //我这里包名是 com.yangyang.test
        //怎么获取加了 自定义注解的 类名 (Demo and Demo2 )
    }
}
