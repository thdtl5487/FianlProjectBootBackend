package com.springboot.react.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.security.dto.MemberRequestDto;
import com.springboot.react.security.dto.MemberResponseDto;
import com.springboot.react.security.dto.TokenDto;
import com.springboot.react.security.jwt.TokenProvider;
import com.springboot.react.security.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
    	System.out.println("id : " + requestDto.getUserid());
    	System.out.println("pw : " + requestDto.getPassword());
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
