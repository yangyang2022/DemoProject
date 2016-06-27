package com.yangyang.Test;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class TestProperty {
    @Test
    public void testPropertyRead() throws IOException {
        Properties prop = new Properties();
        prop.load(TestProperty.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        System.out.println(prop.get("jdbc.username"));
        System.out.println(prop.get("jdbc.password"));
        System.out.println(prop.get("jdbc.url"));
    }
}
