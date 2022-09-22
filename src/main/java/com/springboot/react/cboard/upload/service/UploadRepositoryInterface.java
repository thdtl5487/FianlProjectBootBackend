package com.springboot.react.cboard.upload.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.react.cboard.upload.domain.CBoardAttachVO;

public interface UploadRepositoryInterface extends JpaRepository<CBoardAttachVO, Long>{
	
}
