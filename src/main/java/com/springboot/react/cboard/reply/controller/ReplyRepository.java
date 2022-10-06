package com.springboot.react.cboard.reply.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.springboot.react.cboard.reply.domain.CBoardReplyVO;

@Repository
public class ReplyRepository {

	@Autowired
	private ReplyRepositoryInterface repository;
	
	public ResponseEntity<Map> getPaging(Integer pageNum){
		Map<String, Object> result = null;
		
		Sort sortNum = Sort.by("RNum").descending();
		Pageable pageable = PageRequest.of(pageNum, 10 , sortNum);
		Page<CBoardReplyVO> pageList = repository.findAll(pageable);
		
		List<CBoardReplyVO> replyList = new ArrayList<CBoardReplyVO>();
		
		if(pageList != null && pageList.hasContent()) {
			replyList = pageList.getContent();
		}
		
		HashMap<String, Object> pageInfo = new HashMap<String, Object>();
		int maxPage = pageList.getTotalPages();
		int currentPage = pageList.getNumber();
		
		pageInfo.put("currentPage", currentPage);
		pageInfo.put("maxPage", maxPage);
		
		result = new HashMap<String, Object>();
		result.put("list", replyList);
		result.put("pageInfo", pageInfo);
		
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<Map> getReply(Long bnum){
		Map<String, Object> result = null;
		List<CBoardReplyVO> getReply = null;
		getReply = repository.findByBNum(bnum);
		result = new HashMap<String, Object>();
		result.put("reply", getReply);
		System.out.println("댓글 가져오는지 확인" + result.get("reply"));
		return ResponseEntity.ok(result);
	}

}
