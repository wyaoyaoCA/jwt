package demo.jwt.simple.utils;

import demo.jwt.simple.model.User;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * jwt工具类
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtUtils {

    public String key; // 密钥
    public long ttl;  // 有效时间

    public JwtUtils(String key,long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    public JwtUtils() {
    }

    /**
     * 生成token令牌
     *
     * @param user 用户信息
     * @return
     */
    public String createToken(User user) {
        long now = System.currentTimeMillis();// 当前时间
        long exp = now + this.ttl;
        JwtBuilder jwtBuilder = Jwts.builder().setId(user.getId())
                .setIssuer("terminus") // 签发者
                .setExpiration(new Date(exp)) // 过期时间
                .signWith(SignatureAlgorithm.HS256,this.key)  // 设置加密算法和密钥
                .claim("username", user.getUsername());

        String token = jwtBuilder.compact();
        return token;
    }

    public Claims parseToken(String token) {
        JwtParser parser = Jwts.parser(); // jwt解析工具
        Claims claims = parser.setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }


    // 测试
    public static void main(String[] args) {
        User user= new User("123","wyy","123","");
        JwtUtils jwtUtils = new JwtUtils("wangyaoyao",6000);
        String token = jwtUtils.createToken(user);
        System.out.println(token);
        Claims claims = jwtUtils.parseToken(token);
        System.out.println("=========解析==========");
        System.out.println(claims.getId());
        System.out.println(claims.getIssuer());
        System.out.println(claims.get("username"));
    }

}