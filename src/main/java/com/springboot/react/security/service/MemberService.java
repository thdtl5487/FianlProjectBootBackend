package com.springboot.react.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.react.security.config.SecurityUtill;
import com.springboot.react.security.dto.MemberRequestDto;
import com.springboot.react.security.dto.MemberResponseDto;
import com.springboot.react.security.model.Member;
import com.springboot.react.security.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtill.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public MemberResponseDto changeMemberNickname(String memEmail, String memNickname) {
        Member member = memberRepository.findByMemEmail(memEmail).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        member.setMemNickname(memNickname);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto changeMemberPassword(String memEmail, String exmemPw, String newmemPw) {
        Member member = memberRepository.findById(SecurityUtill.getCurrentMemberId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exmemPw, member.getMemPw())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        member.setMemPw(passwordEncoder.encode((newmemPw)));
        return MemberResponseDto.of(memberRepository.save(member));
}
}