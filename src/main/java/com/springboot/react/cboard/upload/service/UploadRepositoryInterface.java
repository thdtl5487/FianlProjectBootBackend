package com.springboot.react.cboard.upload.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.react.cboard.upload.domain.CBoardAttachVO;

@Repository
public interface UploadRepositoryInterface extends JpaRepository<CBoardAttachVO, Long>{
	
}
