package com.yangyang.Algo;

import com.algs4.stdlib.BinaryStdIn;
import com.yangyang.utils.Queue;
import org.junit.Test;

import java.util.TreeSet;

public class Demo_ST {

    @Test
    public void testDemo1() {
        SequentialST<Integer,String> st = new SequentialST<>();
        st.put(1,"shen");
        st.put(2,"yang");
        st.put(2,"xxx");
        st.put(3,"hehe");
        System.out.println(st.get(1));
        System.out.println(st.get(2));
        System.out.println(st.get(3));
    }

    @Test
    public void testDemo2() {
        BinarySerachBT<Integer,String> st = new BinarySerachBT<>(10);
        st.put(1,"shen");
        st.put(2,"yang");
        st.put(3,"hehe");
        st.put(1,"shen1");
        st.put(2,"shen2");
        System.out.println(st.get(1));
        System.out.println(st.get(2));
        System.out.println(st.get(3));
    }

    @Test
    public void testDemo3() {
        TreeSet<Integer> sets = new TreeSet<>();
        sets.add(1);
        sets.add(9);
        sets.add(6);
        sets.add(3);
        sets.add(5);
        for(int i = 0;i<sets.toArray().length;i++){
            System.out.println(sets.toArray()[i]);
        }
    }

    @Test
    public void testDemo4() {
        BST<Integer,String> tree = new BST<>();

        //tree.put(-4,"shen4");
        //tree.put(5,"shen5");
        //tree.put(-6,"shen6");
        //tree.put(1,"shen1");
        //tree.put(2,"shen2");
        //tree.put(3,"shen3");
        //
        //tree.show(tree.root());
        //System.out.println("size: "+tree.size());
        //System.out.println(tree.floor(6));

        tree.put(5,"5");
        tree.put(3,"3");
        tree.put(7,"7");
        tree.put(2,"2");
        tree.put(4,"4");
        tree.put(6,"6");
        tree.put(8,"8");
        tree.put(9,"9");

        //System.out.println(tree.select(3));
        //tree.show(tree.root());
        //tree.deleteMin();
        //System.out.println("====");
        //tree.show(tree.root());
        //System.out.println("====");
        //tree.delete(5);
        //tree.show();

        tree.keys(2,9).forEach(System.out::println);
        System.out.println("depth: "+tree.depth());

    }

    private static void hannota(int n,char a,char b,char c){
        if(n == 1){
            System.out.println(a+ " --> "+c);
        }else {
            hannota(n-1,a,c,b);
            hannota(1,a,b,c);
            hannota(n-1,b,a,c);
        }
    }
    @Test
    public void testDemo5() {
        //hannota(6,'a','b','c');
        String s = "polygenelubricants";
        System.out.println(s.hashCode());
        byte bs = BinaryStdIn.readByte();
        System.out.println("get: " + bs);
    }
}

class BST<Key extends Comparable<Key> ,Value>{
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int nodeCount;
        public Node(Key key,Value val,int nodeCount){
            this.key = key;
            this.val = val;
            this.nodeCount = nodeCount;
        }
    }
    public int size(){return size(root);}
    public Value get(Key key){return get(root,key);}
    public void put(Key key,Value val){root = put(root,key,val);}

    public Key min(){return min(root).key;}
    public Key floor(Key key){
        Node x = floor(root,key);
        if(x == null) return null;
        return x.key;
    }
    public Key select(int k){
        return select(root,k).key;
    }
    public void deleteMin(){root = deleteMin(root);}
    public void delete(Key key){root = delete(root,key);}

    public void show(){print(root);}
    public Node root(){return root;}

    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new Queue<Key>();
        keys(root,queue,lo,hi);
        return queue;
    }

    public int depth(){return depth(root);}

    //aux function
    private int depth(Node x){
        if(x == null) return 0;
        return Integer.max(depth(x.left),depth(x.right))+1;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left,queue,lo,hi);
        if(cmphi > 0) keys(x.right,queue,lo,hi);
        if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
    }
    private void print(Node root){
        if(root != null){
            print(root.left);
            System.out.println(root.val);
            print(root.right);
        }
    }
    private int size(Node x){
        if(x == null) return 0;
        else return x.nodeCount;
    }
    private Value get(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ) return get(x.left,key);
        else if(cmp > 0 ) return get(x.right,key);
        else return x.val;
    }
    private Node put(Node x,Key key,Value val){
        if(x == null) return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ) x.left = put(x.left,key,val);
        else if(cmp > 0 ) x.right = put(x.right,key,val);
        else x.val = val;
        x.nodeCount = size(x.left) + size(x.right)+1;
        return x;
    }
    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    private Node floor(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);

        if(cmp == 0 ) return x;
        else if(cmp < 0 ) return floor(x.left,key);
        else {
            Node t = floor(x.right,key);
            if( t != null) return t;
            else return x;
        }
        //Node t = floor(x.right,key);
        //if(t != null) return t;
        //else return x;
    }
    private Node select(Node x,int k){
        if(x == null) return null;
        int t = size(x.left);
        if( t > k) return select(x.left,k);
        else if( t < k) return select(x.right,k-t -1);
        else return x;
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.nodeCount = size(x.left)+size(x.right)+1;
        return x;
    }
    private Node delete(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ) x.left = delete(x.left,key);
        else if(cmp > 0 ) x.right = delete(x.right,key);
        else {
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.nodeCount = size(x.left) + size(x.right)+1;
        return x;
    }
}
class BinarySerachBT<Key extends Comparable<Key> ,Value>{
    private Key[] keys;
    private Value[] vals;
    private int n;
    public BinarySerachBT(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public int size(){return n;}
    public int rank(Key key){
        int lo = 0,hi = n-1;
        while (lo <= hi){
            int mid = (lo + hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0 ) hi = mid-1;
            else if(cmp > 0 ) lo = mid+1;
            else return mid;
        }
        return lo;
    }
    public boolean isEmpty(){return n==0;}
    public Value get(Key key){
        if(isEmpty()) return null;
        int i = rank(key);
        if( i < n && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public void put(Key key,Value val){
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0 ){
            vals[i] = val;return;
        }
        for(int j = n;j>i;j--){
            keys[i] = keys[i-1];
            vals[i] = vals[i-1];
        }
        keys[i]= key;vals[i] =val;
        n++;
    }

}
class SequentialST<Key,Value>{
    private Node first;
    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key){
        //serach
        for(Node x = first;x!= null;x = x.next)
            if(key.equals(x.key)) return x.val;
        return null;
    }
    public void put(Key key,Value val){
        if(get(key) != null) return;
        first = new Node(key,val,first);
    }
}
