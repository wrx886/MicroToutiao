package org.example;

import org.example.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Test01_Token {
    @Autowired
    TokenUtil tokenUtil;

    @Test
    public void test01() {
        // 生成 Token
        Map<String, Object> user = new HashMap<>();
        user.put("username", "root");
        user.put("password", "root");
        String token = tokenUtil.dumpToken(user);

        // 解码 Token
        System.out.println(tokenUtil.loadToken(token));
    }
}
