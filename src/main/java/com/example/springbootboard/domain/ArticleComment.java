package com.example.springbootboard.domain;

import java.time.LocalDateTime;

    public class ArticleComment {
    private Long id;
    private Article article;  // 게시글 객체 : ArticleComment 는 Article객체와 관계를 맺는 형태
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
