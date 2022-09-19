package com.springboot.react.nboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service("nboardService")
@RequiredArgsConstructor
public class NBoardService {
   
   // @RequiredArgsConstructor : private final이 붙은 필드의 생성자를 자동으로 추가해주고, @Autowired를 통해 주입도 자동으로 해주는 롬복 애노테이션
   private final NBoardRepository nboardDAO;



   // select 쿼리처럼 조회하는 것이 아닌 insert, update, delete의 경우 @Transactional 애노테이션을 붙여 트랜잭션 처리를 해줘야함
   // (commit, rollback 등이 필요한 쿼리문...)
   @Transactional
   public void insert(NBoardVO vo) {
      nboardDAO.insert(vo);
   }
   
   @Transactional
   public void update(NBoardVO vo) {
      
      nboardDAO.update(vo);
   }
//   
   @Transactional
   public void delete(NBoardVO vo) {
      
      nboardDAO.delete(vo);
   }

   
   public ResponseEntity<Map> getPagingBoard(Integer pageNum){
      return nboardDAO.getPagingBoard(pageNum);
   }
   
   public ResponseEntity<Map> getBoard(Long bnum){
      return nboardDAO.getBoard(bnum);
   }
   
   
}