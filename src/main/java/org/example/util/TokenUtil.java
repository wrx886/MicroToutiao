package org.example.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于对 Token 进行处理的方法
 */
@Component
public class TokenUtil {
    /**
     * Token的有效时间（单位：毫秒）
     */
    @Value("${jwt.token.tokenExpiration}")
    private long tokenExpiration;

    /**
     * Token 进行加密和解密时的密钥
     */
    @Value("${jwt.token.tokenSignKey}")
    private String tokenSignKey;

    /**
     * 生成 Token 字符串
     *
     * @param claims 写入 Token 的数据
     * @return Token 字符串
     */
    public String dumpToken(Map<String, Object> claims) {
        JwtBuilder tokenBuilder = Jwts.builder().
                setSubject("YYGH-USER").
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                signWith(SignatureAlgorithm.HS512, tokenSignKey).
                compressWith(CompressionCodecs.GZIP);

        // 写入数据
        claims.forEach(tokenBuilder::claim);

        // 返回
        return tokenBuilder.compact();
    }

    /**
     * 从 Token 中获取数据，并校验 Token 是否有效
     *
     * @param token Token 字符串
     * @return Token 中存储的数据，Token 无效或过期返回 null
     */
    public Map<String, Object> loadToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
            if (!claims.getExpiration().before(new Date())) {
                // Token 在有效期内
                return new HashMap<>(claims);
            }
        } catch (Exception ignored) {
            // 这里捕获异常即可，不需要处理
        }
        return null;
    }
}
