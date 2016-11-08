package com.yangyang.DesignPattern;

import java.util.Vector;

interface Iterator_1{
    Object next();
    boolean hasnext();
    boolean remove();
}
class ConcreateIterator implements Iterator_1{
    private Vector<String> vector = new Vector<>();
    public int cursor = 0;

    public ConcreateIterator(Vector<String> vector) {
        this.vector = vector;
    }

    @Override
    public Object next() {
        Object result = null;
        if(this.hasnext()){
            result = this.vector.get(cursor++);
        }
        return result;
    }

    @Override
    public boolean hasnext() {
        return this.cursor != vector.size();
    }

    @Override
    public boolean remove() {
        //normally not use
        return this.vector.remove(this.cursor) != null;
    }
}
interface Aggregate{
    void add(String obj);
    void remove(String obj);
    Iterator_1 iterator();
}
class ConcreateAggregate implements Aggregate{
    private Vector<String> vector = new Vector();
    @Override
    public void add(String obj) {
        this.vector.add(obj);
    }

    @Override
    public void remove(String obj) {
        this.vector.remove(obj);
    }

    @Override
    public Iterator_1 iterator() {
        return new ConcreateIterator(this.vector);
    }
}
public class IteratorParttern {


    public static void main(String[] args) {

        Aggregate agg = new ConcreateAggregate();
        agg.add("hello");
        agg.add("world");
        agg.add("shen");
        agg.add("yang");
        agg.add("yang");

        Iterator_1 iter = agg.iterator();
        while (iter.hasnext()){
            System.out.println(iter.next());
        }
    }
}
