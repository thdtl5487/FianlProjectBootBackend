package com.springboot.react.qboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QBoardRepository{
	
	// @PersistenceContext : JPA의 ORM을 처리해주는 EntityManager을 불러올 때 쓰는 애노테이션 입니다.
	@PersistenceContext	
	private final EntityManager em;

	@Autowired
	private QBoardRepositoryInterface qboardRepository;

	public void insert(QBoardVO vo) {
		// em.persist : JPA를 통해 값을 입력할 때 활용합니다.
		QBoardVO newVo = new QBoardVO();
		
		newVo.setBtitle(vo.getBtitle());
		newVo.setBtext(vo.getBtext());
		newVo.setBwriter(vo.getBwriter());
		newVo.setBregDate(vo.getBregDate());
		
		qboardRepository.save(newVo)		 					;
	}
	
	


	   
	// 페이징된 게시물 리스트와 페이징 정보(현재 페이지, 최대 페이지 번호)를 HashMap 타입으로 저장하는 메소드
	public ResponseEntity<Map> getPagingBoard(Integer pageNum){
		Map<String, Object> result = null;
		
		// 1. 리스트 페이징 처리
		Sort sortNum = Sort.by("BNum").descending(); // Sort 정렬 규칙 선언, BNum 역순
		Pageable pageable = PageRequest.of(pageNum, 10, sortNum); // 페이징, 매개변수 : (들어온 웹의 페이지 수, amount, 정렬규칙 적용)
		Page<QBoardVO> pageList = qboardRepository.findAll(pageable); // 페이징된 게시물 리스트를 담는 인스턴스
		
		List<QBoardVO> boardList = new ArrayList<QBoardVO>(); // pageList를 송신할 수 있는 타입으로 저장하는 인스턴스
		
		if(pageList != null && pageList.hasContent()) { // pageList에 값 있는지 체크. hasContent는 값 있으면 true 리턴
			boardList = pageList.getContent(); // pageList의 내용을 boardList에 저장
		}
		// 리스트 페이징 저장 끝 boardList 값 리턴하면 됨.
		
		// 2. 페이지 정보
		HashMap<String, Object> pageInfo = new HashMap<String, Object>();
		int currentPage = pageList.getNumber(); // 현재 페이지값 저장, getNumber는 Page 타입의 내장 메소드
		System.out.println("커런트페이지 : "+ currentPage);
		int maxPage = pageList.getTotalPages(); // 전체 페이지값 저장
		
		pageInfo.put("currentPage", currentPage); // 현재 페이징된 리스트의 번호 저장
		pageInfo.put("maxPage", maxPage); // 마지막 페이지 번호 저장
		
		result = new HashMap<String, Object>();
		result.put("list", boardList); // 페이징된 게시물 리스트를 List라는 key에 저장
		result.put("pageInfo", pageInfo); // 페이징 정보를 pageInfo라는 key에 저장
		
		
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<Map> getBoard(Long bnum) {
		Map<String, Object> result = null;
		System.out.println("getBoard실행?");
		Optional<QBoardVO> getBoard = null;
		getBoard = qboardRepository.findById(bnum);
		System.out.println("getBoard실행22?");
		
		result = new HashMap<String, Object>();
		
		result.put("board", getBoard);
		System.out.println(result.get("board"));
		
		return ResponseEntity.ok(result);
	}
}