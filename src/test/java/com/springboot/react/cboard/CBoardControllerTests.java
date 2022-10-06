package com.springboot.react.cboard;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.react.cboard.reply.controller.ReplyRepositoryInterface;
import com.springboot.react.cboard.reply.domain.CBoardReplyVO;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CBoardControllerTests {
   
	@Autowired
	@Value("${com.community.upload.path}") // application.properties의 변수
	private String uploadPath;
	
	
   @Autowired
   private CBoardService cboardService;
   
   @Autowired
   private CBoardRepositoryInterface repository;
   
   @Autowired
   private EntityManager em;
   
   @Autowired
   private ReplyRepositoryInterface repl;
   
   
   @Test
   public void ReplyTest() {
	   CBoardReplyVO reply = new CBoardReplyVO();
	   CBoardVO vo = new CBoardVO();
	   vo.setBNum(28L);
	   reply.setReply("테스트 댓글");
	   reply.setReplyer("댓글테스터");
	   
	   
	   repl.save(reply);
	   
   }
   @Test
   public void ReplyDelete() {
	   
	   Optional<CBoardReplyVO> cvo = repl.findById(8L);
	   
	   repl.deleteById(8L);
   }

//   @Test
//   public void testInsert() {
//      
//      CBoardVO vo = new CBoardVO();
//      vo.setBtitle("뻨유2");
//      vo.setBtext("뻨2");
//      vo.setBwriter("뻨커2");
//      
//      
//      System.out.println(vo.getBNum());
//      System.out.println(vo.getBtitle());
//      System.out.println(vo.getBwriter());
//      System.out.println(vo.getBtext());
//      repository.save(vo);
//
//      
//   }
//
//   @Test
//   public void testSelectById() {
//      Optional<CBoardVO> CBoard = repository.findById(1L);
//      System.out.println(CBoard);
//
//   }

//   @Test
//   public void testUpdate(CBoardVO RequestVO) {
//      RequestVO.setBNum(3L);
//      CBoardVO vo = new CBoardVO();
//      vo.setBNum(RequestVO.getBNum());
//      
//      vo.setBtitle("수정하겠다");
//      vo.setBtext("오바");
//      repository.save(vo);
//   
//      //테스트를 위해 Long을 지정해주기 위해 이러한 형식으로 테스트 했으며
//      //실제 update와는 다른 부분이 있음
//      
//      System.out.println(vo.getBNum());
//      
//      
//      
//      
//      
//   }
//
//   @Test
//   public void testDelete() {
//      Optional<CBoardVO> vo = repository.findById(3L);
//      repository.deleteById(3L);
//   }
   
   
   private String makeFolder() {

       String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

       String folderPath = str.replace("/", File.separator);

       File uploadPatheFolder = new File(uploadPath,folderPath);

       if(uploadPatheFolder.exists() == false){
           uploadPatheFolder.mkdirs();
       }

       return folderPath;
   }
   
   @Test
   public void uploadtest() {
	   
	   
	   // 이미지 파일만 업로드 가능
     
           // 이미지가 아닌경우 403 Forbidden 반환
          
       
       String originalName = "TestImage.jpg";
	   
	   String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
	 
       String folderPath = makeFolder();
      
       String uuid = UUID.randomUUID().toString();
       
       String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
   }

}