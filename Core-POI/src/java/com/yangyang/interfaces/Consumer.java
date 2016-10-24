package com.yangyang.interfaces;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
