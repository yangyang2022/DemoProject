package com.yangyang.effectiveJava;

public class StacticDemo {

    static int i;

    public static void main(String[] args) {
        if(i == 42) System.out.println("hello world");
        //Set<Integer> set = new TreeSet<>();
        //List<Integer> list = new ArrayList<>();
        //
        //for (int i = -3; i < 3; ++i) {
        //    set.add(i);
        //    list.add(i);
        //}
        //for (int i = 0; i < 3; ++i) {
        //    set.remove(i);
        //    //remove(int index) and remove(object)
        //    list.remove((Integer) i);
        //}
        //System.out.println(set + " : " + list);

        //Comparator<Integer> naturalOrder = new Comparator<Integer>() {
        //    @Override
        //    public int compare(Integer first, Integer second) {
        //        int o1  =first; //auto-unboxing
        //        int o2 = second;
        //        return o1 < o2 ? -1 : (o1 == o2 ? 0 : 1);
        //    }
        //};
        //System.out.println(naturalOrder.compare(new Integer(42),new Integer(42)));


    }
}
