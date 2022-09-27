package com.springboot.react.security.service;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.react.security.dto.MemberRequestDto;
import com.springboot.react.security.dto.MemberResponseDto;
import com.springboot.react.security.dto.TokenDto;
import com.springboot.react.security.jwt.TokenProvider;
import com.springboot.react.security.model.Member;
import com.springboot.react.security.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
	private final AuthenticationManagerBuilder managerBuilder;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	
	//signup 메소드는 평범하게 회원가입을 하는 메소드로, Spring Data JPA의 주요 로직으로 구성된다
	public MemberResponseDto signup(MemberRequestDto requestDto) {
		System.out.println("signup 시작");
        if (memberRepository.existsByMememail(requestDto.getMememail())) {
        	System.out.println("Userid 가지고옴? " + requestDto.getMememail());
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        System.out.println("Userid 가지고옴?222222222 " + requestDto.getMememail());
        
        Member member = requestDto.toMember(passwordEncoder);
        System.out.println(member +"맴버 만들어짐? ");
        System.out.println(requestDto.toMember(passwordEncoder).toString()+"@@@@@@@@@@@@@@@@@@@");
        return MemberResponseDto.of(memberRepository.save(member));
	}
	//1.login 메소드는MemberRequestDto에 있는 메소드 toAuthentication를 통해 생긴 
	//  UsernamePasswordAuthenticationToken 타입의 데이터를 가지게된다.
	
	//2.주입받은 Builder를 통해 AuthenticationManager를 구현한 ProviderManager를 생성한다.
	
	//3.이후 ProviderManager는 데이터를 AbstractUserDetailsAuthenticationProvider 의 
	//  자식 클래스인 DaoAuthenticationProvider 를 주입받아서 호출한다.
	
	//4.DaoAuthenticationProvider 내부에 있는 authenticate에서 
	//  retrieveUser을 통해 DB에서의 User의 비밀번호가 실제 비밀번호가 맞는지 비교한다.
	
	//5.retrieveUser에서는 DB에서의 User를 꺼내기 위해,
	//  CustomUserDetailService에 있는 loadUserByUsername을 가져와 사용한다.
	
	public TokenDto login(MemberRequestDto requestDto) {
		UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
		Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
		System.out.println("로그인 성공?");
		System.out.println("로그인시" + authentication);
		System.out.println("로그인시 토큰이 뭐가 ㄷ담기나?" + authenticationToken);
		return tokenProvider.generateTokenDto(authentication);
	}
}
