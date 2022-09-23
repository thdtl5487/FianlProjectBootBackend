package com.springboot.react.qboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.react.cboard.CBoardRepository;
import com.springboot.react.cboard.CBoardVO;

import lombok.RequiredArgsConstructor;

@Service("qboardService")
@RequiredArgsConstructor
public class QBoardService {
	
	
	private final QBoardRepository qboardDAO;


	

	@Transactional
	public void insert(QBoardVO vo) {
		qboardDAO.insert(vo);
	}
	
	
 
   public ResponseEntity<Map> getPagingBoard(Integer pageNum){
	   return qboardDAO.getPagingBoard(pageNum);
   }
   
   public ResponseEntity<Map> getBoard(Long bnum){
	   return qboardDAO.getBoard(bnum);
   }
	
	
}