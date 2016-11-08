package multiThread;

import com.yangyang.Util.LambdaUtil;
import com.yangyang.Util.SleepUtil;
import com.yangyang.model.Person;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Demo2 {


    private static final String filePath = "C:\\mavenProject\\DemoProject\\SpringNetty\\src\\main\\resources\\demo.txt";
    @Test
    public void testDemo1() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));
        out.writeChars("hello");
        out.flush();

        FileInputStream in = new FileInputStream(filePath);
        int len = in.available();

        byte[] bytes = new byte[len];

        int actlen = in.read(bytes);
        String str = new String(bytes);

        System.out.println(str);

    }

    @Test
    public void testLeakingQueue() {

        LeakingQueue<Integer> leakingQueue = new LeakingQueue<>();
        for (int i = 0; i < 100_000_000; ++i) {
            for (int j = 0; i < 100_000_000; ++i) {
                leakingQueue.enqueue(i);
                leakingQueue.dequeue();
            }
        }

    }

    @Test
    public void testArrayCopy() {

        int size = 10_000_000;
        int[] array = new int[size];
        int[] arrayDestination = new int[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        //System.arraycopy(array,0,arrayDestination,0,size);
        for (int i = 0; i < size; ++i) {
            arrayDestination[i] = array[i];
        }
        System.out.println("time: "+(System.currentTimeMillis()-start));

    }

    @Test
    public void testPerson() throws Exception {

        new Thread(()-> {
            int count = 0;
            while (true){
                System.out.println("hello world "+(count++));
                SleepUtil.SleepMills(1);
            }
        }).start();

        Person person = new Person();
        System.out.println("person: "+person.getName());
        System.out.println(person.clonePerson());


    }

    @Test
    public void testEquals() {
        Integer[] array = {1, 2, 3};
        Integer[] array2 = {1,2,39};
        boolean isEq = Arrays.deepEquals(array,array2);
        System.out.println(isEq);

    }

    @Test
    public void testUrl() {

        String url = "http://www.example.org/uid/alex/docid/1/title/MyfirstBlog/hehe/hehhehe";

        Matcher matcher = Pattern.compile("^.*/uid/(?<uid>.*)/docid/(?<docid>.*)/title/(?<title>.*)").matcher(url);

        if(matcher.matches()){
            System.out.println(matcher.group("uid") + " : " + matcher.group("docid") + " : " + matcher.group("title"));
        }
    }

    @Test
    public void testFIFO() {

        final int cacheSize = 3;
        LinkedHashMap<Integer,String> lru = new LinkedHashMap<Integer,String>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };
        lru.put(1,"1");
        lru.put(2,"2");
        lru.put(3,"3");
        lru.put(4,"4");
        lru.put(5,"5");

        System.out.println(lru.size());
        LambdaUtil.printMap(lru);

    }

    @Test
    public void testLRU() {

        final int cacheSize = 100;
        Map<String,String> lrucache = new LinkedHashMap<String,String>((int)Math.ceil(cacheSize/0.75f),0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };
    }
    class LeakingQueue<T>{

        private List<T> backendList = new ArrayList<T>();
        private int topIndex = 0;

        public void enqueue(T value){
            backendList.add(value);
        }
        public T dequeue(){
            T result = backendList.get(topIndex);
            topIndex++;
            return result ;
        }
    }
}
