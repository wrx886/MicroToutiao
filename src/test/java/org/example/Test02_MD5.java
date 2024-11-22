package org.example;

import org.example.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test02_MD5 {
    @Test
    public void test() {
        System.out.println(MD5Util.encrypt("123456"));
    }

}
