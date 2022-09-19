package com.springboot.react.nboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NBoardRepositoryInterface extends JpaRepository<NBoardVO, Long>{
	
}
