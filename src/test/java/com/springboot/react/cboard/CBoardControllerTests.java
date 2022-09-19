package com.springboot.react.cboard;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CBoardControllerTests {
	
	@Autowired
	private CBoardService cboardService;
	
	@Autowired
	private CBoardRepositoryInterface repository;
	
	@Autowired
	private EntityManager em;

//	@Test
//	public void testInsert() {
//		
//		CBoardVO vo = new CBoardVO();
//		vo.setBtitle("뻨유2");
//		vo.setBtext("뻨2");
//		vo.setBwriter("뻨커2");
//		
//		
//		System.out.println(vo.getBNum());
//		System.out.println(vo.getBtitle());
//		System.out.println(vo.getBwriter());
//		System.out.println(vo.getBtext());
//		repository.save(vo);
//
//		
//	}
//
//	@Test
//	public void testSelectById() {
//		Optional<CBoardVO> CBoard = repository.findById(1L);
//		System.out.println(CBoard);
//
//	}

	@Test
	public void testUpdate(CBoardVO RequestVO) {
		RequestVO.setBNum(3L);
		CBoardVO vo = new CBoardVO();
		vo.setBNum(RequestVO.getBNum());
		
		vo.setBtitle("수정하겠다");
		vo.setBtext("오바");
		repository.save(vo);
	
		//테스트를 위해 Long을 지정해주기 위해 이러한 형식으로 테스트 했으며
		//실제 update와는 다른 부분이 있음
		
		System.out.println(vo.getBNum());
		
		
		
		
		
	}
//
//	@Test
//	public void testDelete() {
//		Optional<CBoardVO> vo = repository.findById(3L);
//		repository.deleteById(3L);
//	}

}
