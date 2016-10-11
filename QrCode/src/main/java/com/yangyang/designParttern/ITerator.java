package com.yangyang.designParttern;

interface iterator{
    void first();
    String next();
    Boolean isDone();
    String currentItem();
}
interface ISubject_itor{
    iterator CreateIterator();
}
class Arts implements ISubject_itor{
    private String[] subjects;

    public Arts() {
        subjects = new String[5];
        subjects[0] = "shanghai";
        subjects[1] = "beijing1";
        subjects[2] = "beijing2";
        subjects[3] = "beijing3";
        subjects[4] = "beijing4";
    }

    @Override
    public iterator CreateIterator() {
        return new ArtIter(subjects);
    }
    private class ArtIter implements iterator{
        private String[] subjects;
        private int position;
        public ArtIter(String[] subjects){
            this.subjects = subjects;
            position = 0;
        }
        @Override
        public void first() {
            position = 0;
        }

        @Override
        public String next() {
            return subjects[position++];
        }

        @Override
        public Boolean isDone() {
            return position >= subjects.length;
        }

        @Override
        public String currentItem() {
            return subjects[position];
        }
    }
}
public class ITerator {
    private static void print(iterator iter){
        while (!iter.isDone()){
            System.out.println(iter.next());
        }
    }
    public static void main(String[] args) {
        ISubject_itor sub = new Arts();
        iterator iter = sub.CreateIterator();
        //print(iter);
    }
}
