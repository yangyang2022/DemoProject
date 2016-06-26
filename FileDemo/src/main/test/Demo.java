import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Demo {
    @FunctionalInterface
    interface F<A, B, C, D, E> {
        void accept(int a, int b, int c, int d, F f);
    }
    private static boolean isEven(int e){
        return (e&1) == 0;
    }
    @Test
    public void testDemo() {
        Consumer print = System.out::println;
        List<Integer> list = Stream.generate(()->(int)(Math.random()*100)).limit(10).collect(Collectors.toList());
        for (int i = 0; i < list.size(); ++i) {
            if(isEven(list.get(i))) list.remove(list.get(i));
        }
        list.forEach(print);
    }
}
