package com.springboot.react.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.springboot.react.security.jwt.JwtAccessDeniedHandler;
import com.springboot.react.security.jwt.JwtAuthenticationEntryPoint;
import com.springboot.react.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {
	
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	// 먼저 request로부터 받은 비밀번호를 암호화하기 위해 PasswordEncoder 빈을 생성
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http	
				//https만을 사용하기위해 httpBasic을 disable
				.httpBasic().disable()
				//우리는 리액트에서 token을 localstorage에 저장할 것이기 때문에 csrf 방지또한 disable했다.
				.csrf().disable()
				//우리는 REST API를 통해 세션 없이 토큰을 주고받으며 데이터를 주고받기 때문에 세션설정또한 STATELESS로 설정했다.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
				.and()
				//예외를 핸들링하는 것에서는 이전에 작성했던 JwtAuthenticationEntryPoint와
				//JwtAccessDeniedHandler를 넣었다.
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)
				
				.and()
				// 모든 Requests에 있어서 /auth/**를 제외한 모든 uri의 request는 토큰이 필요하다. 
				// /auth/**는 로그인 페이지를 뜻한다.
				.authorizeHttpRequests()
				.antMatchers("/auth/**").permitAll()
//				.anyRequest().authenticated()
				// 모든 요청을 인증된 사용자만 접속할 수 있도록 함
				.anyRequest().permitAll()
				// 모든 요청을 모두에게 접속 허가함
				
				.and()
				//마지막으로 전에 설정한 JwtSecurityConfig클래스를 통해 tokenProvider를 적용시킨다.
				.apply(new JwtSecurityConfig(tokenProvider));
		return http.build();
				
	}
	
	
}
