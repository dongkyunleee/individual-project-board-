package com.example.boardproject.Repository;

import com.example.boardproject.Entity.Article;
import com.example.boardproject.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 이름
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        // 이거 안됨 나도 잘 모르겠음 워로ㅓㅇㄴ롸머노리ㅓㅁ너림나(해결해야하는데 2시간동안 헤맴)
        // 부가 : 아니 데이터도 대입도 다 잘했는데 왜 비교가 안되지? 진짜 화나네요. 코드가 틀린거는 절대 아님
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L,"당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article,"park","노트북");
            Comment b = new Comment(2L, article,"kim","아바타");
            Comment c = new Comment(3L, article,"lee","쇼생크의 탈출");
            List <Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }

        // 과제임
        /* Case 3 : 9번 게시글의 모든 댓글 조회 */ {

        }

        /* Case 4 : 9999번 게시글의 모든 댓글 조회 */ {

        }

        /* Case 5 : -1번 게시글의 모든 댓글 조회 */{

        }
    }
    @Test
    @DisplayName("특정 닉네임 모든 댓글 조회")
    void findByNickname() {
        /* Case 1 : "park"의 모든 댓글 조회 */
        // 부가 : 아니 맞는데 왜 다르다고 나오냐고 확인 다 했는데
        {
            // 입력 데이터 준비
                String nickname = "park";
            // 실제 수행
                List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"),nickname,"노트북");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"),nickname,"치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ "),nickname,"게임");
            List <Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"park의 모든 데이터 출력!");
        }
        // 과제
        /* Case 2 : "kim"의 모든 댓글 조회 */{
            // 입력 데이터 준비
            String nickname = "kim";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"),nickname,"아바타");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"),nickname,"피자");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ "),nickname,"자전거");
            List <Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"kim의 모든 데이터 출력!");
        }
        /* Case 3 : null 의 모든 댓글 조회 */ {

        }
        /* Case 4 : ""의 모든 댓글 조회 */ {

        }
        /* Case 5 : "i"의 모든 댓글 조회 */ {

        }
    }
}