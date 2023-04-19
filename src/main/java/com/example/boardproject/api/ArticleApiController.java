package com.example.boardproject.api;

import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Entity.Article;
import com.example.boardproject.Repository.ArticleRepository;
import com.example.boardproject.Service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
@Slf4j
@RestController //@Rest Controller란 Rest Api용 컨트롤러이다 그리고 JSON형태를 반환해준다.

public class ArticleApiController {
     @Autowired // DI 생성 객체 가져와 연결함!
     private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")  //JSON으로 보내는 거기 떄문에 dto선언 하지만 ArticleForm도 Articledto로 해야하지만 혼동을 안주기위해 설정
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ? // 삼항연산자임
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable long id,
                                          @RequestBody ArticleForm dto) {
        Article updated =articleService.update(id, dto);
        return(updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //트랜잭션 -> 실패! -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
       List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
 }
