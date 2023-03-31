package com.example.boardproject.api;

import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Entity.Article;
import com.example.boardproject.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController //@Rest Controller란 Rest Api용 컨트롤러이다 그리고 JSON형태를 반환해준다.

public class ArticleApiController {
    @Autowired // DI = 외부에서 가져온는 말이다
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    // POST
    @PostMapping("/api/articles")  //JSON으로 보내는 거기 떄문에 dto선언 하지만 ArticleForm도 Articledto로 해야하지만 혼동을 안주기위해 설정
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }


    // PATCH

    // DELETE
}
