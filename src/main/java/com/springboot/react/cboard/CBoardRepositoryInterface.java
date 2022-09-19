package com.springboot.react.cboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CBoardRepositoryInterface extends JpaRepository<CBoardVO, Long>{
	
}
