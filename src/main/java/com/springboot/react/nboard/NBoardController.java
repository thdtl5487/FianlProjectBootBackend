package com.springboot.react.nboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Notice")

public class NBoardController {

   // @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
   private final NBoardService nboardService;
   private final NBoardRepositoryInterface repository;
  

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
   public void insert(NBoardVO vo) {
      nboardService.insert(vo);
      
   }
   
   
   @PostMapping("/modify.do")
   public void update(NBoardVO Requestvo) {
        NBoardVO  vo = repository.findById(Requestvo.getBNum()).orElseThrow(() -> {
              return new IllegalArgumentException("수정에 실패하였습니다.");
          });
        
        vo.setBtitle(Requestvo.getBtitle());
        vo.setBtext(Requestvo.getBtext());
        repository.save(vo);
      
   }

   // 게시글 삭제
   @PostMapping("/delete.do")
   public void delete(NBoardVO vo) {
      nboardService.delete(vo);
      
   }
   

}