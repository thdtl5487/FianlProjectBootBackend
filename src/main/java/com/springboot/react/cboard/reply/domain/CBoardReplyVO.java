package com.springboot.react.cboard.reply.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.react.cboard.CBoardVO;

import lombok.Data;

@Entity
@Data
@Table(name = "CBoardReply")
public class CBoardReplyVO {
	
	@Id	// PK 설정
	@GeneratedValue
	private Long RNum;
	
	@Column(name = "Replyer")
    private String Replyer;
	
	@Column(name = "Reply")
    private String Reply;
	
	@CreationTimestamp
	@JsonFormat(pattern = "MM-dd hh:mm", timezone = "Asia/Seoul")
	@Column(columnDefinition = "date default sysdate", name = "BregDate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date ReplyDate;
	
	@Column(name = "BNum")
    private Long BNum;
    
    
}
