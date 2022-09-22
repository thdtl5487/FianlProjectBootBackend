package com.springboot.react.security.service;

import java.util.Collections;
import java.util.Optional;

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
	public UserDetails loadUserByUsername(String mememail) throws UsernameNotFoundException {
		System.out.println(mememail + "@!@@@@@@@@2");
		
		 return memberRepository.findByMememail(mememail)
	                .map(this::createUserDetails)
	                .orElseThrow(() -> new UsernameNotFoundException(mememail + " 을 DB에서 찾을 수 없습니다"));
	}
	
	private UserDetails createUserDetails(Member member) {
		System.out.println("creatUserDtails 실행하냐!!!!!!!!!" + member.getMememail());
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemrole().toString());
		
		return new User(
				String.valueOf(member.getMemnum()),
				member.getMempw(),
				Collections.singleton(grantedAuthority)
				);
				
				
		
		}
	}
	
