package com.example.springbootboard.repository;

import com.example.springbootboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @Repository 어노테이션 추가하지 않아도 됨 : JpaRepository의 구현체인 SimpleJpaRepository에 이미 추가되어 있음
@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>
{
}
