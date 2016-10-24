package Algo;

import org.junit.Test;

import java.util.BitSet;
import java.util.Currency;
import java.util.Locale;

public class Demo2 {

    @Test
    public void testDemo0() {
        Currency.getAvailableCurrencies()
                .forEach(e-> System.out.printf("%s -- %s -- %s \n",
                        e.getDisplayName(),e.getDisplayName(Locale.ENGLISH),e.getNumericCode()));
    }

    @Test
    public void testDemo2() {
        BitSet bm=new BitSet();
        System.out.println(bm.isEmpty()+"--"+bm.size());
        bm.set(0);
        System.out.println(bm.isEmpty()+"--"+bm.size());
        bm.set(1);
        System.out.println(bm.isEmpty()+"--"+bm.size());
        System.out.println(bm.get(65));
        System.out.println(bm.isEmpty()+"--"+bm.size());
        bm.set(65);
        System.out.println(bm.isEmpty()+"--"+bm.size());
    }

    @Test
    public void testDemo3() {

    }
}
