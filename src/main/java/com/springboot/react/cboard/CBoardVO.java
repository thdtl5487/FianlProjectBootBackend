package com.springboot.react.cboard;

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
@Table(name = "CBoardTABLE") // 테이블 이름
@Data		
public class CBoardVO {
	
	

	@Id	// PK 설정
	@GeneratedValue
	@Column(name = "BNum")	// @Column : 컬럼 이름
//	@OneToMany(mappedBy = "BNum")
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
	
	
	@Column(name = "fullName")
	private String fullName;
	
    @Column(columnDefinition = "integer default 0", name = "Hits")    
    private int Hits;

    
    @Column(columnDefinition = "integer default 0", name = "relies")
    private int replies;
    
//	@OneToMany(mappedBy = "cboard_BNum", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//	@OrderBy("id asc") // 댓글 정렬
//	private List<CBoardReplyVO> Reply = new ArrayList<CBoardReplyVO>();
	
}