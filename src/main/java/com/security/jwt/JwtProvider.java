package com.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.security.services.UserPrinciple;

//import java.security.SignatureException;
//import java.util.Date;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component 
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${grokonez.app.jwtSecret}")
    private String jwtSecret;

    @Value("${grokonez.app.jwtExpiration}")
    private int jwtExpiration;

    // tạo jwt
    public String generateJwtToken(Authentication authentication) {  

    	// lấy principal trong authentication
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        // tạo jwt
        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }

    // lấy username từ jwt
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }

    // xác thực jwt
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
        	// chữ kí jwt không hợp lệ
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
        	// mã jwt không hợp lệ
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	// jwt hết hạn
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
        	// jwt không hỗ trợ
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
        	// jwt trống
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
}
