package com.springboot.react.qboard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity									
@Table(name = "QBoardTABLE") // 테이블의 이름을 설정 (SPRINGBOOT_CRUD)
@Data		
public class QBoardVO{

	@Id	// PK 설정
	@GeneratedValue
	@Column(name = "BNum")	// @Column : 컬럼의 이름을 설정 (ARTICLE_ID)
	private Long BNum;
	
	@Column(name = "Btitle")
	private String Btitle;
	
	@Column(name = "Btext")
	private String Btext;
	
	@CreationTimestamp
	@JsonFormat(pattern = "MM-dd hh:mm", timezone = "Asia/Seoul")
	@Column(columnDefinition = "date default sysdate", name = "BregDate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date BregDate;
	
	@Column(name = "Bwriter")
	private String Bwriter;


	
	
}