package com.yangyang.Algo;

import com.algs4.stdlib.In;
import com.yangyang.utils.Bag;
import org.junit.Test;

import java.io.File;

public class Demo_gragh {

    private static int degree(Gragh g,int v){
        int degree = 0;
        for(int w:g.adj(v)) degree++;
        return degree;
    }
    private static int maxDegree(Gragh g){
        int max =0;
        for(int v = 0;v<g.Vertex();v++)
            if(degree(g,v)>max) max = degree(g,v);
        return max;
    }
    private static double avgDegree(Gragh g){
        return 2.0*g.Edge() / g.Vertex();
    }
    public static int numberOfSelfLoops(Gragh g){
        int count =0;
        for(int v=0;v<g.Vertex();v++)
            for(int w:g.adj(v)) if(v == w) count++;
        return count/2;
    }
    @Test
    public void testDsemo1() {

        String filePath = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";

        Gragh g = new Gragh(new In(new File(filePath)));
        //System.out.println(g.toString());
        System.out.println("degree: "+degree(g,3));
        System.out.println("maxDegree: "+maxDegree(g));
        System.out.println("avgDegree: "+avgDegree(g));
        System.out.println("selfLoop: "+numberOfSelfLoops(g));
    }
}

class Gragh{
    private  int v;//顶点数量
    private int e;//边的数目
    private Bag<Integer>[] adj;//邻接表
    public Gragh(int v){
        //构建v个顶点,0条边的图
        this.v = v;this.e = 0;
        adj = new Bag[v];
        for(int i=0;i<v;i++)
            adj[i] = new Bag<>();
    }
    public Gragh(In in){
        this(in.readInt());
        int e = in.readInt();
        for(int i=0;i<e;i++){
            //添加一条边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }
    public int Vertex(){return v;}
    public int Edge(){return e;}
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s = v +" vertexs, "+e+" edges\n";
        for(int i = 0;i<v;i++){
            s += i+": ";
            for(int w:this.adj(i)) s += w+" ";
            s+="\n";
        }
        return s;
    }
}
