package com.yangyang.Algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo_trie {

    @Test
    public void testDemo1() {
        WordDictionary world = new WordDictionary();
        world.addWord("bad");
        world.addWord("dad");
        world.addWord("mad");
        System.out.println(world.search("pad"));
        System.out.println(world.search("bad"));
        System.out.println(world.search(".ad"));
        System.out.println(world.search("b.."));
    }
}
class WordDictionary {

    private Map<Integer,List<String>> maps = new HashMap<>();

    // Adds a word into the data structure.
    public void addWord(String word) {
        int index = word.length();
        if(!maps.containsKey(index)){
            List<String> list = new ArrayList<>();
            list.add(word);
            maps.put(index,list);
        }else maps.get(index).add(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        int index = word.length();
        if(!maps.containsKey(index)) return false;

        List<String> list = maps.get(index);
        if(isword(word)) return list.contains(word);
        for(String s:list){
            //if(issame(word,s)) return true;
            if(s.matches(word)) return true;
        }
        return false;
    }
    private boolean isword(String word){
        for(int i=0;i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
    private boolean issame(String w,String s){
        if(w.length() != s.length()) return false;
        for(int i=0;i<w.length();i++){
            if(w.charAt(i) != '.' && w.charAt(i) != s.charAt(i)) return false;
        }
        return true;
    }
}