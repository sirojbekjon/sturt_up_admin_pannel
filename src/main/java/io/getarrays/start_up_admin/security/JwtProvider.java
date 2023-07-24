package io.getarrays.start_up_admin.security;

import io.getarrays.start_up_admin.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    static String secretKey = "SIROJSMS1712ZIYADILLAYEVERGASHVICH";
    static long expireTime = 36_000_000;
    public String generateToken(String username, Role role){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("role",role.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public String getUserNameFromToken(String token){
        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;

        }catch (Exception e){
            e.getMessage();
        }
        return "Not found";
    }



}
