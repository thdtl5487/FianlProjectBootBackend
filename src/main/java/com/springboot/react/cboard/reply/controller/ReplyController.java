package com.springboot.react.cboard.reply.controller;

import java.util.Map;

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
	
	@PostMapping("/delete")
	public void ReplyDelete() {
		
	}

}
