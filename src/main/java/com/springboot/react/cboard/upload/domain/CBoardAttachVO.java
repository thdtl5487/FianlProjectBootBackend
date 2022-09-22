package com.springboot.react.cboard.upload.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.springboot.react.cboard.CBoardVO;


import lombok.Data;



@Entity
@Data
@Table(name = "CBoardAttach")
public class CBoardAttachVO {
	
	@Id	// PK 설정
	private Long CANum;
	
	@Column(name = "fileName")
    private String fileName;
	
	@Column(name = "uuid")
    private String uuid;
	
	@Column(name = "folderPath")
    private String folderPath;
	
	
	@Column(name = "fullName")
	private String fullName;
  
	  
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cboard_BNum", referencedColumnName = "BNum")
//    private CBoardVO cboard_BNum;
//    
    
    

}
