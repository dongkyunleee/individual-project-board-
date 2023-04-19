package com.example.boardproject.Entity;

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
}
