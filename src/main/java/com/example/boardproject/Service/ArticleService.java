package com.example.boardproject.Service;

import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Entity.Article;
import com.example.boardproject.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성)
public class ArticleService {
    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
       return articleRepository.findAll();
    }

    public Article show(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(long id, ArticleForm dto) {
        //1. 수정용 Entity 를 생성한다.
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());

        //2. 대상 Entity 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리 (대상이 없거나 , ID가 없을 경우)
        if (target == null || id != target.getId()) {
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return null;
        }
        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }
    @Transactional // 해당 메소드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 Entity 묶음으로 변환!
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // Entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );

        // 결과 값 반환
        return articleList;
    }
}
