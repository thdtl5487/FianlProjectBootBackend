package com.springboot.react.cboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service("cboardService")
@RequiredArgsConstructor
public class CBoardService {
   
   // @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
   private final CBoardRepository cboardDAO;


   
//   public CBoardVO selectById(Integer bnum) {
//      return cboardDAO.selectById(bnum);
//   }

   // select 쿼리처럼 조회하는 것이 아닌 insert, update, delete의 경우 @Transactional 애노테이션을 붙여 트랜잭션 처리를 해줘야함
   // (commit, rollback 등이 필요한 쿼리문...)
   @Transactional
   public void insert(CBoardVO vo) {
      cboardDAO.insert(vo);
   }
   
   @Transactional
   public void update(CBoardVO vo) {
      
      cboardDAO.update(vo);
   }
//   
   @Transactional
   public void delete(CBoardVO vo) {
      
      cboardDAO.delete(vo);
   }
//   @Transactional
//   public void update(CBoardVO vo, String Btitle, String Btext,String bwriter) {
//      CBoardVO selected = cboardDAO.selectById(vo.getBNum());
//      selected.setBtitle(Btitle);
//      selected.setBtext(Btext);
//      selected.setBwriter(bwriter);
//   }
   
//   @Transactional
//   public void delete(CBoardVO vo) {
//      CBoardVO selected = cboardDAO.selectById(vo);
//      cboardDAO.delete(selected);
//   }
   
//   
//   public List<CBoardVO> getList(CBoardVO vo){
//         return cboardDAO.getList(vo);
//   }
//   
   public ResponseEntity<Map> getPagingBoard(Integer pageNum){
      return cboardDAO.getPagingBoard(pageNum);
   }
   
   public ResponseEntity<Map> getBoard(Long bnum){
      return cboardDAO.getBoard(bnum);
   }
   
   
}