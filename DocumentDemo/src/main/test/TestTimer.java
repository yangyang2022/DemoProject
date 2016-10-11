import com.yangyang.quartz.HelloTask;
import org.junit.Test;

import java.util.Timer;

public class TestTimer {
    @Test
    public void testTimer1() throws InterruptedException {
        HelloTask task = new HelloTask();
        Timer timer = new Timer("hello");
        timer.schedule(task,0,2000);

        Thread.sleep(10000);
    }
}
