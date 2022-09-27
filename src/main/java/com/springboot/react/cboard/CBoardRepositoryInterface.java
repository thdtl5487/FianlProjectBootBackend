package com.springboot.react.cboard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CBoardRepositoryInterface extends JpaRepository<CBoardVO, Long>{
	
	@Transactional
	@Modifying
	@Query(value = "update CBoardTABLE set Hits = Hits+1 where BNum = ?", nativeQuery = true )    
	int updateHits(Long id);
	
	@Query(value = "select COUNT(*) from cboard_reply where BNum = ?", nativeQuery = true)
	int replyCounts(Long id);
	
}
