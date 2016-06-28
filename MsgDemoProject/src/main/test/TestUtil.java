
import com.yangyang.Utils.MsgUtil;
import org.junit.Test;

import java.util.Date;

public class TestUtil {
    @Test
    public void testMsgTime() {
        System.out.println(MsgUtil.formateDate(new Date()));
    }
}
