package com.springboot.react.security.dto;

import com.springboot.react.security.model.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//토큰의 값을 헤더에서 뽑거나 헤더에서 삽입할때 쓰는 dto다.
public class MemberResponseDto {
   
   private String mememail;
   private String memnickname;
   
   public static MemberResponseDto of(Member member) {
      return MemberResponseDto.builder()
            .mememail(member.getMememail())
            .memnickname(member.getMemnickname())
            .build();
   }
}