package com.springboot.react.cboard.reply.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.cboard.CBoardVO;
import com.springboot.react.cboard.reply.domain.CBoardReplyVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/CReply")
public class ReplyController{
	
	private final ReplyRepositoryInterface repository;
	
	private final ReplyRepository replyRes;
	
	
	@GetMapping("/read")
	public ResponseEntity<Map> viewCReply(@RequestParam(value = "rnum")Long rnum){
		System.out.println("/read 됨?");
		
		return null;
	}
	
	
	@PostMapping("/insert")
	public void ReplyInsert(CBoardReplyVO reply) {
		CBoardReplyVO newRe = new CBoardReplyVO();
		System.out.println("로그"  + reply.getReply());
		newRe.setBNum(reply.getBNum());
		newRe.setReply(reply.getReply());
		newRe.setReplyer(reply.getReplyer());
		repository.save(newRe);
		
		
	}
	
	@PostMapping("/modify")
	public void ReplyModify() {
		
	}
	
	   @PostMapping("/deleteReply")
		public void ReplyDelete(CBoardReplyVO reqVo) {
		   System.out.println("머받음?"+reqVo);
			Optional<CBoardReplyVO> cvo = repository.findById(reqVo.getRNum());
			repository.deleteById(reqVo.getRNum());;
		}
	
	@GetMapping("/replyList")
	public ResponseEntity<Map> viewReplyList(@RequestParam(value = "pageNum", required = false)Integer pageNum){
		System.out.println("viewReplyList 실행 ");
		if (pageNum == null || pageNum <= 0) {
			pageNum = 0;
		}
		return replyRes.getPaging(pageNum);
	}
//	
	@GetMapping("/ReplyRead")
	public ResponseEntity<Map> viewReply(@RequestParam(value = "bnum", required = false) Long bnum){
		
		return replyRes.getReply(bnum);
	}
	
	@GetMapping("/Reply")
	public List<CBoardReplyVO> readReply(Long BNum){
		
		System.out.println("이거 실행 하나요?" + repository.findByBNum(BNum));
		return repository.findByBNum(BNum);
		
	}
	
	

}
