package com.example.boardproject.Service;


import com.example.boardproject.DTO.CommentDto;
import com.example.boardproject.Entity.Article;
import com.example.boardproject.Entity.Comment;
import com.example.boardproject.Repository.ArticleRepository;
import com.example.boardproject.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        //  댓글 목록 조회
//      List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 변환 : 엔티티 -> DTO
//       List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        //stream 문법임
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        // 게시글조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                //orElseThrow란? : 만약에 없다면 예외를 발생 시킨다 라는 뜻.
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 ! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        // 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패 ! 대상 댓글이 없습니다"));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }
}
