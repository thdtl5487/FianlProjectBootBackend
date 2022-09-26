package com.springboot.react.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//비밀번호를 수정할 때 쓰이는 dto다.
//이전의 비밀번호가 제대로 입력하지 않는다면 실행되지 않는다.
public class ChangePasswordRequestDto {
	
	private String userid;
	private String expassword;
	private String newpassword;
}
