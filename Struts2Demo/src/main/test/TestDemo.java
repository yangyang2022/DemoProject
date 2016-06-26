import org.junit.Test;

import java.util.stream.IntStream;

public class TestDemo {

    private static int printNum(int num){
        int result = 0;
        String[] strs = Integer.toBinaryString(num).split("");
        for (int i = 0; i < strs.length; ++i) {
            if(strs[i].equals("1"))result += Math.pow(3,(strs.length-i-1));
        }
        return result;
    }
    @Test
    public void testDemo() {
        IntStream.iterate(0,(n)->n+1)
                .map(e->printNum(e))
                .limit(25)
                .forEach(e-> System.out.print(e+","));

    }
}
