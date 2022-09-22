package com.springboot.react.security.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.react.security.model.Member;
import com.springboot.react.security.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
		System.out.println(memEmail + "@!@@@@@@@@2");
		 return memberRepository.findByMemEmail(memEmail)
	                .map(this::createUserDetails)
	                .orElseThrow(() -> new UsernameNotFoundException(memEmail + " 을 DB에서 찾을 수 없습니다"));
	}
	
	private UserDetails createUserDetails(Member member) {
		System.out.println("creatUserDtails 실행하냐!!!!!!!!!" + member);
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemRole().toString());
		
		return new User(
				String.valueOf(member.getMemNum()),
				member.getMemPw(),
				Collections.singleton(grantedAuthority)
				);
				
				
		
		}
	}
	
