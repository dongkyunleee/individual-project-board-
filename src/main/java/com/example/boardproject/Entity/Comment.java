package com.example.boardproject.Entity;

import com.example.boardproject.DTO.CommentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다
    @JoinColumn(name = "article_id") // "articleid" 컬럼에 Article 대표값을 저장!
    public Article article;

    @Column
    public String nickname;

    @Column
    public String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 처리
        if(dto.getId() != null )
            throw new IllegalArgumentException("댓글 생성 실패 ! 댓글의 id가 없어야 합니다");
        if(dto.getArticleId() != article.getId()) // 아이디가 다른 경우
            throw new IllegalArgumentException("댓글 생성 실패 ! 댓글의 id가 잘못 됐습니다");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );

    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패 ! 잘못된 id 입력 되었습니다.");

        // 객체를 갱신
        // dto에 getNickname이 null이 아니라면 즉 있다면 this.nickname을 json 닉네임으로 갱신해준다.
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();

            // dto에 getBody가 null이 아니라면 즉 있다면 this.body를 json body로 갱신해준다.
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
