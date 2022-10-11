package com.springboot.react.security.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.security.dto.MemberRequestDto;
import com.springboot.react.security.dto.MemberResponseDto;
import com.springboot.react.security.dto.TokenDto;
import com.springboot.react.security.jwt.TokenProvider;
import com.springboot.react.security.model.Member;
import com.springboot.react.security.repository.MemberRepository;
import com.springboot.react.security.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    private final TokenProvider TP;
    
    private final MemberRepository MR;
        
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
    	System.out.println(requestDto.getMememail() + "@@@@@@@@@@@@@@@@@");
    	System.out.println(requestDto.getMempw() + "#################");
        return ResponseEntity.ok(authService.login(requestDto));
    } 
    
    @PostMapping("/loginsuccess")
    public ResponseEntity<Optional<Member>> loginsuccess(@RequestBody TokenDto userToken){
    	 
    	System.out.println("받아온값 확인 : " + userToken.getAccessToken());
    	
    	System.out.println("유저정보 나옴? : " + TP.getAuthentication(userToken.getAccessToken()).getName());
    	
    	Long memNum = Long.parseLong(TP.getAuthentication(userToken.getAccessToken()).getName());
    	
    	Optional<Member> result = MR.findById(memNum);
    	
    	System.out.println(result);
    	
    	return ResponseEntity.ok(result);
    }
    
    @PostMapping("/loginCheck")
    public ResponseEntity<Member> loginCheck(@RequestBody TokenDto token){
    	
		Long memNum = Long.parseLong(TP.getAuthentication(token.getAccessToken()).getName());
		
		Member result = MR.findByMemnum(memNum);
		
		return ResponseEntity.ok(result);
    }
}
