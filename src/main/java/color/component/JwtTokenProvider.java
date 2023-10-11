package color.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {
    static Long EXPIRE_TIME = 1000 * 60 * 60L;
    static String secretKey = "my-color-project-1234567890";

    public static String createToken(long userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public static String getUserId(String token) {
        return extractClaims(token).get("userId").toString();
    }

    public static boolean isExpired(String token) {
        Date expiredDate = extractClaims(token).getExpiration();
        return expiredDate.before(new Date());
    }

    private static Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
