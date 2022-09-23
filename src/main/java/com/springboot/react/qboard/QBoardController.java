package com.springboot.react.qboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor



@RequestMapping("/QnA")


public class QBoardController {

	
	private final QBoardService qboardService;
	private final QBoardRepositoryInterface repository;
	


	@GetMapping("/getList.do")
	public ResponseEntity<Map> viewQBoardList(@RequestParam(value = "pageNum", required = false)Integer pageNum){
		System.out.println("@@@viewQBoardList 실행@@@@");
		if(pageNum == null || pageNum <= 0) {
			pageNum = 0;
		}
		return qboardService.getPagingBoard(pageNum);
	}



	
	@GetMapping("/view.do")
	public ResponseEntity<Map> viewQBoard(@RequestParam(value="bnum", required = false)Long bnum){
		
		System.out.println("/view.do 테스트"+qboardService.getBoard(bnum));
		
		return qboardService.getBoard(bnum);
	}


	@PostMapping("/insertProcess.do")
	public void insert(QBoardVO vo) {
		System.out.println("살려주시세요");
		System.out.println("실행 안됐을ㅇ듯");
		qboardService.insert(vo);
		
	}
	

	
	@PostMapping("/modify.do")
	public void update(QBoardVO RequestVo) {
		  System.out.println("실행함?" + RequestVo.getBNum());
		  QBoardVO  vo = repository.findById(RequestVo.getBNum()).orElseThrow(() -> {
		        return new IllegalArgumentException("수정에 실패하였습니다.");
		    });
		  
		  vo.setBtitle(RequestVo.getBtitle());
		  vo.setBtext(RequestVo.getBtext());
		  repository.save(vo);
		
	}

	
	@PostMapping("/delete.do")
	public void delete(QBoardVO RequestVo) {
		System.out.println("삭제 시도는 하냐?" + RequestVo.getBNum());
	    QBoardVO  vo = repository.findById(RequestVo.getBNum()).orElseThrow(() -> {
	        return new IllegalArgumentException("삭제에 실패하였습니다.");
	    });
	  
	  repository.delete(vo);
		
	}
	


}