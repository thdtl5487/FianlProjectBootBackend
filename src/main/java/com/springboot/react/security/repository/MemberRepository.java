package com.springboot.react.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.react.security.model.Member;

//JpaRepository 를 상속하면 자동 컴포넌트 스캔됨.
//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 IoC 됩니다. 이유는 JpaRepository를 상속했기 때문입니다.
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByUserid(String userid);
	boolean existsByUserid(String userid);

	//간단하게 JPARepository를 사용했다.
	//email로 User를 찾는 로직과, userid가 존재하는가 판별하는 로직을 추가한다.
	
}
