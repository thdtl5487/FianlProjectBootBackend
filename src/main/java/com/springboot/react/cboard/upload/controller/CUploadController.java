package com.springboot.react.cboard.upload.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.react.cboard.upload.domain.CUploadResultDTO;

import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("CUpload")
@RestController
public class CUploadController {

    @Value("${com.community.upload.path}") // application.properties의 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<CUploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        List<CUploadResultDTO> resultDTOList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false){
                // 이미지가 아닌경우 403 Forbidden 반환
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            
            String originalName = uploadFile.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);// 실제 이미지 저장
                resultDTOList.add(new CUploadResultDTO(fileName, uuid, folderPath));
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                
                System.out.println("thumbnailFile ===== " + thumbnailFile);
                
                // 섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 400,400);
                
                
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        File uploadPatheFolder = new File(uploadPath,folderPath);

        if(uploadPatheFolder.exists() == false){
            uploadPatheFolder.mkdirs();
        }

        return folderPath;
    }
    
    @GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {

		System.out.println("getFile() param fileName ===== " + fileName);
        
        File file = new File(uploadPath, fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("헤더 값이 뭐임?" + result.getHeaders());
		return result;
	}
    
	
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName){
		String srcFileName = null;
		System.out.println("removeFile() param fileName ===== " + fileName);
		try {
			
		    srcFileName = URLDecoder.decode(fileName, "UTF-8");
		    
		    System.out.println("srcFileName ===== " + srcFileName);
		    
		    File file = new File(uploadPath + File.separator + srcFileName);
		    
		    System.out.println("file ===== " + file);
		    
		    
		    boolean result = file.delete();
		    
		    System.out.println("fiel.delete() result ===== " + result);
		
		    File thumbnail = new File(file.getParent(), "s_" + file.getName());
		    
		    System.out.println("thumbnail ===== " + thumbnail);
		    
		    
		    result = thumbnail.delete();
		    
		    System.out.println("thumbnail.delete() result ===== " + result);
		
		    return new ResponseEntity<>(result,HttpStatus.OK);
		
	    } catch (UnsupportedEncodingException e){
	        e.printStackTrace();
	        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
    
}