package com.springboot.react.cboard.reply.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.react.cboard.reply.domain.CBoardReplyVO;




@Repository
public interface ReplyRepositoryInterface extends JpaRepository<CBoardReplyVO, Long>, JpaSpecificationExecutor<CBoardReplyVO>{
	

	@Query(value = "select * from cboard_reply where bnum = ? order by rnum desc",
			nativeQuery = true)
	public List<CBoardReplyVO>findByBNum(Long BNum);
	


}
