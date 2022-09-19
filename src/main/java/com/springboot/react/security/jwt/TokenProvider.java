package com.springboot.react.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JComboBox.KeySelectionManager;

import org.aspectj.weaver.Dump.INode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.react.security.dto.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j;
import sun.util.logging.resources.logging;

@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
    private final Key key;
	
    
    //@Value 어노테이션으로 yml에 있는 secret key를 가져온 
    //다음 이것을 decode한다  이후 의존성이 주입된 key의 값으로 정한다.
	//여기서 @Value는 'springframwork.beans.factory.annotation.Value 소속임
    // lombok의 @Value와 착각하지 말것!
    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
    	byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    	this.key = Keys.hmacShaKeyFor(keyBytes);
    	
    }
    
    //토큰생성
    //Authentication 인터페이스를 확장한 매개변수를 받아서 그 값을 string으로 변환한다.
    //현재시각과 만료시각을 만든 후 Jwts의 builder를 이용하여 Token을 생성한 
    //다음 TokenDto에 생성한 token의 정보를 넣는다.
    public TokenDto generateTokenDto(Authentication authentication) {
    	
    	String autorities = authentication.getAuthorities().stream()
    			.map(GrantedAuthority::getAuthority)
    			.collect(Collectors.joining(","));
    	
    	long now = (new Date()).getTime();
    	
    	Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
    	
    	System.out.println(tokenExpiresIn);
    	
    	String accessToken = Jwts.builder()
    			.setSubject(authentication.getName())
    			.claim(AUTHORITIES_KEY, autorities)
    			.setExpiration(tokenExpiresIn)
    			.signWith(key, SignatureAlgorithm.HS512)
    			.compact();
    	
    	return TokenDto.builder()
    			.grantType(BEARER_TYPE)
    			.accessToken(accessToken)
    			.tokenExpiresIn(tokenExpiresIn.getTime())
    			.build();
    	
    }
    
    //토큰을 받았을때 토큰의 인증을 꺼내는 메소드
    //서술할 parseClaims 메소드로 string 형태의 토큰을 claims형태로 생성한다.
    //다음 auth가 없으면 exception을 반환한다.
    //GrantedAuthority을 상속받은 타입만이 사용 가능한 Collection을 반환한다
    //그리고 stream을 통한 함수형 프로그래밍으로 claims형태의 토큰을 알맞게 정렬한 이후
    //SimpleGrantedAuthority형태의 새 List를 생성한다. 여기에는 인가가 들어있다.
    
    //SimpleGrantedAuthority은 GrantedAuthority을 상속받았기 때문에 이 지점이 가능하다.

    public Authentication getAuthentication(String accessToken) {
    	Claims claims = parseCalims(accessToken);
    	
    	if(claims.get(AUTHORITIES_KEY) == null) {
    		throw new RuntimeException("권한 정보가 없는 토큰입니다");
    	}
    	
    	Collection<? extends GrantedAuthority> authorities = 
    			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
    			.map(SimpleGrantedAuthority::new)
    			.collect(Collectors.toList());
    	
    	UserDetails principal = new User(claims.getSubject(), "", authorities);
    	
    	return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    	
    	
    }
    
    public boolean validateToken(String token) {
    	try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SignatureException | MalformedJwtException e) {
			System.out.println("잘못된 JWT 서명입니다");
		} catch(ExpiredJwtException e) {
			System.out.println("만료된 JWT 토큰입니다");
		} catch(UnsupportedJwtException e) {
			System.out.println("지원되지 않는 JWT 토큰입니다");
		} catch(IllegalArgumentException e) {
			System.out.println("JWT 토큰이 잘못되었습니다.");
		}
    	return false;
    }
    
    
    
    private Claims parseCalims(String accesToken) {
    	try {
    		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accesToken).getBody();
    	}catch (ExpiredJwtException e) {
    		return e.getClaims();
    	}
    }
   
	

}
