package com.yangyang.utils;

import java.util.Optional;

public class OPtionalUtils {
    public static Optional<Integer> stringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
