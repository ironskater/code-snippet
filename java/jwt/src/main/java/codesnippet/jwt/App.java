package codesnippet.jwt;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter.Feature;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    private static SecretKey key = Jwts.SIG.HS256.key().build();

    public static void main(String[] args) {

        String jwt = generateJWT("123", "hyde liao");
        log.info("Generated JWT: {}", jwt);

        decodeJWT(jwt);
    }

     public static String generateJWT(String userId, String username) {

        long expirationTime = 1000 * 60 * 60; // 1 hour in milliseconds
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Payload payload = new Payload(userId, username, "admin");

        return Jwts.builder()
                .subject(userId)
                .claim("payload", payload)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

     public static void decodeJWT(String jwt) {

        try {
            Jws<Claims> jws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt);

            Claims claims = jws.getPayload();

            log.info("Decoded JWT: {}", JSON.toJSONString(claims, Feature.PrettyFormat));

        } catch (Exception ex) {
            log.error("Failed to decode JWT", ex);
        }
    }


    @AllArgsConstructor
    @Getter
    private static class Payload {

        private String username;
        private String userId;
        private String authority;
    }
}
