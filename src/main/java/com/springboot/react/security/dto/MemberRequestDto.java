package com.springboot.react.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.react.security.model.Authority;
import com.springboot.react.security.model.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
	
	private String mememail;
	private String mempw;
	private String memnickname;
	
	 public Member toMember(PasswordEncoder passwordEncoder) {
	        return Member.builder()
	                .mememail(mememail)
	                .mempw(passwordEncoder.encode(mempw))
	                .memnickname(memnickname)
	                .memrole(Authority.ROLE_USER)
	                .build();
	    }
	
	//Request를 받을 때 쓰이는 dto다. UsernamePasswordAuthenticationToken를 반환하여
	//아이디와 비밀번호가 일치하는지 검증하는 로직을 넣을 수 있게 된다.
	 public UsernamePasswordAuthenticationToken toAuthentication() {
		 System.out.println("로그인성공 ");
	        return new UsernamePasswordAuthenticationToken(mememail, mempw);
	    }
}
