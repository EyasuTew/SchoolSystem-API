package com.product.school.security;

import com.product.school.data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "tolusecret";

    public User validate(String token) {

        User jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return jwtUser;
    }
}
