package com.springboot.react.cboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CBoardRepositoryInterface extends JpaRepository<CBoardVO, Long>{
	
}
