package com.springboot.react.qboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
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
		System.out.println("qBoardInsert 실행여부 확인");
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
	
	@PostMapping("/answer.do")
	public void answer(QBoardVO reqQboardVO) {
		System.out.println("qboardAnswer 실행확인");
		System.out.println("qboardVO 데이터 : "+reqQboardVO);
		
		QBoardVO qboardVO = repository.findById(reqQboardVO.getBNum()).orElseThrow(()->{
			System.out.println("else 오류 발생");
			return null;
		});
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
//		date.setMonth(11); 테스트용 setMonth
		qboardVO.setBAnswerText(reqQboardVO.getBAnswerText());
		qboardVO.setBAnswerRegdate(date); // 답글 다는 날짜 갱신
		
		repository.save(qboardVO);
	}


}