package com.springboot.react.cboard.upload.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class CUploadResultDTO {

	@Id
	private Long CANum;
	
	@Column(name = "fileName")
    private String fileName;
	@Column(name = "uuid")
    private String uuid;
	@Column(name = "folderPath")
    private String folderPath;
	
	@Column(name = "fullName")
	private String fullName;
    
    
    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return "";
        }
    }
    
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return "";
        }
    }

	public CUploadResultDTO(String fileName, String uuid, String folderPath) {
		this.fileName = fileName;
		this.uuid = uuid;
		this.folderPath = folderPath;
	}
    
}