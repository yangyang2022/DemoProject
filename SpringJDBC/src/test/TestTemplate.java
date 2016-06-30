import com.yangyang.template.myJdbcCon;
import org.junit.Test;

public class TestTemplate {
    @Test
    public void testTemplate1() {
        myJdbcCon mt = new myJdbcCon();
        mt.add(1);
        mt.delete(2);
    }
}
