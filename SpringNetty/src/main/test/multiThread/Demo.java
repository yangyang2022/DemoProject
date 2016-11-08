package multiThread;

import com.yangyang.Util.SleepUtil;
import com.yangyang.multiThread.ForkJoinDemo;
import com.yangyang.multiThread.ThreadPool;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class Demo {

    private static final String filePath = "C:\\mavenProject\\DemoProject\\SpringNetty\\src\\main\\resources\\demo.txt";

    private static void startProcessNormal() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe","-c","netstat","-a");
        Process process = pb.start();
        Files.copy(process.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    }
    @Test
    public void testDemo1() throws IOException {

        //startProcessNormal();
        //File file = Paths.get(filePath).toFile();
        //file.renameTo(new File(file.getParent()+File.separator+file.getName().replace("e","999")));

        //FileUtils.renameFile(filePath,"99","e");

        ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","dir");
        pb.redirectOutput(Paths.get(filePath).toFile());
        pb.start();

    }

    private class unSharedDemo implements Runnable{
        private int count = 5;

        public unSharedDemo(int count) {
            this.count = count;
        }
        @Override
        public void run() {
            while (count > 0){
                count--;
                System.out.println(Thread.currentThread().getName()+" ,count: "+count);
            }
        }
    }

    @Test
    public void testDemo2() {
        //new Thread(new unSharedDemo(40)).start();
        //new Thread(new unSharedDemo(40)).start();
        //new Thread(new unSharedDemo(40)).start();

        unSharedDemo shareThread = new unSharedDemo(100);

        Thread aa = new Thread(shareThread,"aa");
        Thread bb = new Thread(shareThread,"bb");
        Thread cc = new Thread(shareThread,"cc");
        aa.start();
        bb.start();
        cc.start();

        SleepUtil.sleep3s();
    }

    @Test
    public void testDemo3() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_00; ++i) {
            new Thread(()-> SleepUtil.sleep1s()).start();
        }
        System.out.println("time1: "+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_00; ++i) {
            ThreadPool.getInstance().start(()-> SleepUtil.sleep1s());
        }
        System.out.println("time2: "+(System.currentTimeMillis()-start));
    }

    @Test
    public void testDemo4() {
        LongAdder adder = new LongAdder();
        System.out.println(adder.intValue());
        adder.increment();
        System.out.println("incre: "+adder.intValue());
        adder.decrement();
        System.out.println("decre: "+adder.longValue());

    }

    private static Long runForkJoin(long number) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.submit(new ForkJoinDemo(0,number)).get();
    }
    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {

        long number = 1_000_000_000;
        System.out.println("result: "+runForkJoin(number));

        SleepUtil.sleep5s();

    }

    @Test
    public void testAtomic() {
        LongAdder adder = new LongAdder();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; ++i) {
            service.submit(() -> {
                int count = 10;
                while (count-- < 0){
                    adder.increment();
                }
            });
        }
        SleepUtil.sleep3s();
        System.out.println("sum: " + adder.sum());

    }

    @Test
    public void testCOW() {
        //copy-on-write
        List<Integer> list = new CopyOnWriteArrayList<>();
        //list.add(123);
        //list.add(23123);
        //LambdaUtil.printList(list);
    }
}
