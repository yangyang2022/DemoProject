package com.yangyang.interfaces;

@FunctionalInterface
public interface Function<T,R> {
    R apply(T t,String s);
}
