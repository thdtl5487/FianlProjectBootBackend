package com.springboot.react.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
        name="IDX_SEQ_GEN",		// 시퀀스 생성기의 이름을 지정
        sequenceName="IDX_SEQ",	// 시퀀스의 이름을 지정 (IDX_SEQ)
        initialValue=1,			// 시퀀스의 초기값을 설정
        allocationSize=1		// 시퀀스의 증가량을 설정
        )

/*
 * id, email, password, nickname로 구성되어 있으며, Spring Security에는 user의 role이 필요하므로
 * enum타입의 role을 추가하였다.
 * 
 * 이후 닉네임과 비밀번호 변경에 필요한 함수인 setNickname과 setPassword를 추가했다.
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Member_Table")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	// 시퀀스 생성기를 통해 시퀀스를 만들고 ID에 자동으로 주입
	generator = "IDX_SEQ_GEN")
	private long id;
	
	private String userid;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	private Authority authority;
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Builder
	public Member(long id, String userid, String password, String nickname, Authority authority) {
		this.id = id;
		this.userid = userid;
		this.password = password;
		this.nickname = nickname;
		this.authority = authority;
	}
	
	
	
	
}
