package com.springboot.react.qboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface QBoardRepositoryInterface extends JpaRepository<QBoardVO, Long>, CrudRepository<QBoardVO, Long>{
   
	QBoardVO save(QBoardVO vo);
	
	void delete(QBoardVO vo);
	

	
}