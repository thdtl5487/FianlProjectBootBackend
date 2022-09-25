package com.springboot.react.nboard.upload.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "NBoardAttach")
public class NBoardAttachVO {
	
	@Id
	private Long NANum;
	
	@Column(name = "fileName")
    private String fileName;
	
	@Column(name = "uuid")
    private String uuid;
	
	@Column(name = "folderPath")
    private String folderPath;
	
	@Column(name = "fullName")
	private String fullName;
  
	@Column(name = "BNum")
	private Long BNum;
	  
}
