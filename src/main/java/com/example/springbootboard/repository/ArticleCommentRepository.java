package com.example.springbootboard.repository;

import com.example.springbootboard.domain.ArticleComment;
import com.example.springbootboard.domain.QArticle;
import com.example.springbootboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @Repository 어노테이션 추가하지 않아도 됨 : JpaRepository의 구현체인 SimpleJpaRepository에 이미 추가되어 있음
@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); // 원하지 않은 항목 제외
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like검색 편리하게
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like검색 편리하게
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like검색 편리하게
    }
}
