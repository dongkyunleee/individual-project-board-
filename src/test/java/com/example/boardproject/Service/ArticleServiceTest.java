package com.example.boardproject.Service;




import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Test

    void index() {
        //예상
        Article a = new Article(1L, "가가가가" , "1111");
        Article b = new Article(2L, "나나나나" , "2222");
        Article c = new Article(3L, "다다다다" , "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        //실제
        List<Article> articles = articleService.index();
        //비교
        //assertEquals = 두개의 값이 같은지 비교 메소드
        assertEquals(expected.toString(),articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id,"가가가가", "1111");
        // 실제
       Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지않는_id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
            Article article = articleService.show(id); // show에 null값을 넣었기 때문에 똑같이 예상 expectid도 null값을 넣어서 비교
        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional //테스트 종료후 변경된 데이터를 롤백(처음으로 되돌림)처리
    void create_성공_title과_content만_있는_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional // 그리고 조회말고 생성,변경,삭제 돨수 있는 경우에는 Transactional으로 롤백 할 수 있게 변경을 해줘여 한다.
    void create_실패_id가_포함된_있는_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    //과제 : 생각해서 한번 해보자

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 예상
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        // 실제
        Article article = articleService.update(id,dto);
        // 비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {

    }

    @Test
    @Transactional
    void update_실패_존재하는_않는_id의_dto_입력() {

    }

    @Test
    @Transactional
    void update_실패_id만_있는_dto_입력() {

    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {

    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {

    }
}