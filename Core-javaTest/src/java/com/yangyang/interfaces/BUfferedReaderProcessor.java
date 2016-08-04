package com.yangyang.interfaces;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BUfferedReaderProcessor {

    String process(BufferedReader br) throws IOException;

}
