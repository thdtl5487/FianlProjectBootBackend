package com.springboot.react.cboard.reply.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.react.cboard.reply.domain.CBoardReplyVO;
@Repository
public interface ReplyRepositoryInterface extends JpaRepository<CBoardReplyVO, Long>{

}
