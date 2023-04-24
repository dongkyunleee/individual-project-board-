package com.example.boardproject.DTO;

import com.example.boardproject.Entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") // JsonProperty란? : 받은 Json을 article_id라는 데이터를
    // private Long articleId에 자동으로 mapping해준다 즉 받은 메소드를 기존에 있던 articleId에 종속하게 만들어준다
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
