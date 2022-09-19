package com.springboot.react.cboard;

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

//@RequestMapping("/community")

@RequestMapping("/Community")


public class CBoardController {

   // @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
   private final CBoardService cboardService;
   private final CBoardRepositoryInterface repository;
   

//   @GetMapping("/getList.do")
//   public ResponseEntity<Map> viewCBoardList(@RequestParam(value = "pageNum", required = false)Integer pageNum){
//      System.out.println("@@@viewCBoardList 실행@@@@");
//      if(pageNum == null || pageNum <= 0) {
//         pageNum = 1;
//      }
//      return cboardService.getPagingBoard(pageNum);
//   }

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

//   @PutMapping("/view.do")
   
   // 게시글 등록
//   @PostMapping(value = "/insertProcess.do")
//   public void insert(CBoardVO vo) {
//      cboardService.insert(vo);
//   }
   
   // 아래 모든 메소드에 request.setAttribute("article", articleService.selectById(vo))를 해주는것과 같은 역할
//   @ModelAttribute("cboard")
//   public CBoardVO getArticle(CBoardVO vo) {
//      return cboardService.selectById(vo);
//   }
//   
//   @ModelAttribute("cboardList")
//   public ResponseEntity<Map> getListForm(Integer pageNum){
//      return cboardService.getPagingBoard(pageNum);
//   }
//   
//   // 홈 화면
//   @RequestMapping("/")
//   public String home() {
//      return "index";
//   }
//   
//   // 게시글 등록 폼
//   @GetMapping("/community/write.do")
//   public String insertForm() {
//      return "views/insertForm";
//   }
//   
   // 게시글 등록
   @PostMapping("/insertProcess.do")
   public void insert(CBoardVO vo) {
      System.out.println("살려주시세요");
      System.out.println("실행 안됐을ㅇ듯");
      cboardService.insert(vo);
      
   }
   
   // 게시글 조회
//   @GetMapping("/read.do")
//   public CBoardVO selectById(CBoardVO vo) {
//      vo = cboardService.selectById(vo);
//      System.out.println(vo);
//      return vo;
//      
//   }
   
   // 게시글 수정 폼
//   @PutMapping("/modify.do")
//   public CBoardVO updateForm(int BNum) {
//      cboardService.update(BNum, String Btitle, Btext, bwriter);
//   }
//   
//   // 게시글 수정
//   @PutMapping("/modify.do")
//   public void update(@RequestBody CBoardVO Requestvo, Long BNum) {
//        System.out.println("실행함?" + Requestvo.getBNum());
//        CBoardVO  vo = repository.findById(BNum).orElseThrow(() -> {
//              return new IllegalArgumentException("수정에 실패하였습니다.");
//          });
//        
//        vo.setBtitle(Requestvo.getBtitle());
//        vo.setBtext(Requestvo.getBtext());
//        repository.save(vo);
//      
//   }
   
   @PostMapping("/modify.do")
   public void update(CBoardVO Requestvo) {
        System.out.println("실행함?" + Requestvo.getBNum());
        CBoardVO  vo = repository.findById(Requestvo.getBNum()).orElseThrow(() -> {
              return new IllegalArgumentException("수정에 실패하였습니다.");
          });
        
        vo.setBtitle(Requestvo.getBtitle());
        vo.setBtext(Requestvo.getBtext());
        repository.save(vo);
      
   }
//   
//   // 게시글 삭제 폼
//   @GetMapping("/community/delete.do")
//   public String deleteForm() {
//      return "views/deleteForm";
//   }
//   
   // 게시글 삭제
   @PostMapping("/delete.do")
   public void delete(CBoardVO vo) {
      cboardService.delete(vo);
      
   }
   
//
//   
//   @GetMapping("/community/getList.do")
//   public String getList(CBoardVO vo) {
//      cboardService.getList(vo);
//     return "views/getListForm";
//   }

}