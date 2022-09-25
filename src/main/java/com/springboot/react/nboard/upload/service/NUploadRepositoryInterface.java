package com.springboot.react.nboard.upload.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.react.nboard.upload.domain.NBoardAttachVO;

@Repository
public interface NUploadRepositoryInterface extends JpaRepository<NBoardAttachVO, Long> {
	
}
