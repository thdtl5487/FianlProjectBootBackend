package com.springboot.react.nboard;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.nboard.upload.domain.NBoardAttachVO;
import com.springboot.react.nboard.upload.service.NUploadRepositoryInterface;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Notice")

public class NBoardController {
   
	@Value("${com.notice.upload.path}") // application.properties의 변수
	private String uploadPath;

	// @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
	private final NBoardService nboardService;
	private final NBoardRepositoryInterface repository;
	private final NUploadRepositoryInterface uRepository;
  

	@GetMapping("/getList.do")
	public ResponseEntity<Map> viewNBoardList(@RequestParam(value = "pageNum", required = false)Integer pageNum){
		if(pageNum == null || pageNum <= 0) {
			pageNum = 0;
		}
		return nboardService.getPagingBoard(pageNum);
	}

   
	@GetMapping("/view.do")
	public ResponseEntity<Map> viewNBoard(@RequestParam(value="bnum", required = false)Long bnum){
      
		return nboardService.getBoard(bnum);
	}


   // 게시글 등록
	@PostMapping("/insertProcess.do")
	public void insert(NBoardVO vo, NBoardAttachVO nvo) {
		NBoardVO newVo = new NBoardVO();
		NBoardAttachVO newNvo = new NBoardAttachVO();
  
		if(nvo.getUuid() != null) {
			newNvo.setFolderPath(nvo.getFolderPath().replace('\\', '/'));
			newNvo.setUuid(nvo.getUuid());
			newNvo.setFileName(nvo.getFileName());
			newNvo.setFullName(nvo.getFolderPath().replace('\\', '/') + "/s_" + nvo.getUuid() + "_" + nvo.getFileName());
			newVo.setFilePath(newNvo.getFullName());
		}
  
		newVo.setBtitle(vo.getBtitle());
		newVo.setBtext(vo.getBtext());
		newVo.setBregDate(vo.getBregDate());
  
		repository.save(newVo);
  
		newNvo.setNANum(newVo.getBNum());
		uRepository.save(newNvo);
	}
   
   
	@PostMapping("/modify.do")
	public void update(NBoardVO Requestvo, NBoardAttachVO RequestNvo) {
		NBoardVO  vo = repository.findById(Requestvo.getBNum()).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
        
		vo.setBtitle(Requestvo.getBtitle());
		vo.setBtext(Requestvo.getBtext());
        
        NBoardAttachVO nvo =  uRepository.findById(Requestvo.getBNum()).orElseThrow(() -> {
        	return new IllegalArgumentException("이미지 수정에 실패하였습니다.");
        });
        
        if(RequestNvo.getUuid() != null) {
            nvo.setFolderPath(RequestNvo.getFolderPath().replace('\\', '/'));
            nvo.setUuid(RequestNvo.getUuid());
            nvo.setFileName(RequestNvo.getFileName());
            nvo.setFullName(RequestNvo.getFolderPath().replace('\\', '/') + "/s_" + nvo.getUuid() + "_" + nvo.getFileName());
            vo.setFilePath(nvo.getFullName());
        }
    
        repository.save(vo);
        uRepository.save(nvo);
      
	}

	// 게시글 삭제
	@PostMapping("/delete.do")
	public void delete(NBoardVO vo) {
		nboardService.delete(vo);
      
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
}