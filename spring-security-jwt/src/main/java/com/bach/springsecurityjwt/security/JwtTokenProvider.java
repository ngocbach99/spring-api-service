package com.bach.springsecurityjwt.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.bach.springsecurityjwt.dao.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j //simple log facade for Java 
public class JwtTokenProvider {
	
	//Đoạn JWT_SECRET này là bí mật và chỉ có phía server biết.
	private final String JWT_SECRET = "ngocbach";
	
	//Thời gian có hiệu lực của chuỗi JWT
	private final long JWT_EXPIRATION = 604800000L; //Expiration : Hết hạn
	
	//Tạo ra JWT từ htoong tin của user
	public String generateToken(CustomUserDetails userDetails) {
		
		Date now = new Date();
		
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		
		//Tạo chuỗi JWT từ id của User
		return  Jwts.builder()
					.setSubject(Long.toString(userDetails.getUser().getId()))
					.setIssuedAt(now) // thời điểm phát hành là ngay bây giờ.
					.setExpiration(expiryDate) // xét ngày hết hạn.
					.signWith(SignatureAlgorithm.HS256, JWT_SECRET) //xét kiểu mã hóa chữ kí và chữ kí của ta.
					.compact();
	}
	
	//Lấy thông tin user từ JWT
	public String getUserFormJWT(String token) {
		return Jwts.parser()
				   .setSigningKey(JWT_SECRET)
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token"); //Invalid : không hợp lệ
		} catch (ExpiredJwtException ex) {
			log.error("Expried JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token"); 
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty");
		}
		return false;
	}
}
