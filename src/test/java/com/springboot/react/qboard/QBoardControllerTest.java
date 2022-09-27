package com.springboot.react.qboard;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QBoardControllerTest {

	@Autowired
	private QBoardService qboardService;
	
	@Autowired
	private QBoardRepositoryInterface repo;
	
	@Autowired
	private EntityManager em;
	
	@Test
	public void InsertTest() {
		
		QBoardVO vo = new QBoardVO();
		vo.setBtitle("테스트 제목");
		vo.setBtext("테스트 내용");
		vo.setBwriter("테스트 글쓴이");
		
		qboardService.insert(vo);
		
	}
	
	


}
