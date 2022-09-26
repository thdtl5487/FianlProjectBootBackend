package com.springboot.react.cboard;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.cboard.reply.controller.ReplyRepositoryInterface;
import com.springboot.react.cboard.reply.domain.CBoardReplyVO;
import com.springboot.react.cboard.upload.domain.CBoardAttachVO;
import com.springboot.react.cboard.upload.service.CUploadRepositoryInterface;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Community")
public class CBoardController {
	
	private final ReplyRepositoryInterface Rrepository;
	
	@Value("${com.community.upload.path}") // application.properties의 변수
    private String uploadPath;
	
	
	private final CBoardRepository cboardDAO;
	   
  

	// @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
	private final CBoardService cboardService;
	private final CBoardRepositoryInterface repository;
	private final CUploadRepositoryInterface uRepository;
   



	@GetMapping("/getList.do")
	public ResponseEntity<Map> viewCBoardList(@RequestParam(value = "pageNum", required = false)Integer pageNum){
		System.out.println("@@@viewCBoardList 실행@@@@");
		if(pageNum == null || pageNum <= 0) {
			pageNum = 0;
		}
		return cboardService.getPagingBoard(pageNum);
	}



   
	@GetMapping("/view.do")
	public ResponseEntity<Map> viewCBoard(@RequestParam(value="bnum", required = false)Long bnum){
	      
		System.out.println("/view.do 테스트"+cboardService.getBoard(bnum));
      
		return cboardService.getBoard(bnum);
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
   

  
	// 게시글 등록
	@PostMapping("/insertProcess.do")
	public void insert(CBoardVO vo, CBoardAttachVO cvo) {
   
		CBoardVO newVo = new CBoardVO();
		CBoardAttachVO newCvo = new CBoardAttachVO();
      
		if(cvo.getUuid() != null) {
			newCvo.setFolderPath(cvo.getFolderPath().replace('\\', '/'));
			newCvo.setUuid(cvo.getUuid());
			newCvo.setFileName(cvo.getFileName());
			newCvo.setFullName(cvo.getFolderPath().replace('\\', '/') + "/s_" + cvo.getUuid() + "_" + cvo.getFileName());
			newVo.setFullName(newCvo.getFullName());
		}
      
		newVo.setBtitle(vo.getBtitle());
		newVo.setBtext(vo.getBtext());
		newVo.setBwriter(vo.getBwriter());
		newVo.setBregDate(vo.getBregDate());
      
		repository.save(newVo);
      
		newCvo.setCANum(newVo.getBNum());
		uRepository.save(newCvo);
      
	}
   
	@PostMapping("/modify.do")
	public void update(CBoardVO Requestvo, CBoardAttachVO RequestCvo) {
		System.out.println("실행함?" + Requestvo.getBNum());
        CBoardVO  vo = repository.findById(Requestvo.getBNum()).orElseThrow(() -> {
        	return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        
        vo.setBtitle(Requestvo.getBtitle());
        vo.setBtext(Requestvo.getBtext());
        
        CBoardAttachVO cvo =  uRepository.findById(Requestvo.getBNum()).orElseThrow(() -> {
        	return new IllegalArgumentException("수정에 실패했소다.");
        });
        
        if(RequestCvo.getUuid() != null) {
            cvo.setFolderPath(RequestCvo.getFolderPath().replace('\\', '/'));
            cvo.setUuid(RequestCvo.getUuid());
            cvo.setFileName(RequestCvo.getFileName());
            cvo.setFullName(RequestCvo.getFolderPath().replace('\\', '/') + "/s" + cvo.getUuid() + "_" + cvo.getFileName());
            vo.setFullName(cvo.getFullName());
        }
    
        repository.save(vo);
        uRepository.save(cvo);
      
	}

	// 게시글 삭제
	@PostMapping("/delete.do")
	public void delete(CBoardVO vo) {
		cboardDAO.delete(vo);
      
	}
   
	@PostMapping("/deleteReply")
	public void ReplyDelete(CBoardReplyVO reqVo) {
		System.out.println("머받음?"+reqVo);
		Optional<CBoardReplyVO> cvo = Rrepository.findById(reqVo.getRNum());
		Rrepository.deleteById(reqVo.getRNum());;
	}
	
}