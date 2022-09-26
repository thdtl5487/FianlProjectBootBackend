package com.springboot.react.security.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.react.security.jwt.JwtFilter;
import com.springboot.react.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	//직접 만든 TokenProvider와 JwtFilter를 SecurityConfig에 적용할 때 사용한다.
	private final TokenProvider tokenProvider;
	
	//메인 메소드인 configure은TokenProvider를 주입받아서 JwtFilter를 통해 
	//SecurityConfig 안에 필터를 등록하게 되고, 스프링 시큐리티 전반적인 필터에 적용된다
	@Override
	public void configure(HttpSecurity http) {
		JwtFilter customFilter = new JwtFilter(tokenProvider);
		System.out.println("너 토큰값 받아? : " + customFilter);
		System.out.println("넌 뭘 출력함? : " + http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class));
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
}
