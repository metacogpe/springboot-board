package com.example.springbootboard.service;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.dto.ArticleUpdateDto;
import com.example.springbootboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;  // JUnit 대신 assertj 사용
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    // mock을 대상에 주입하기 위해 @InjectMocks 사용
    @InjectMocks private ArticleService sut;   // 테스트해야 할 클래스 불러오기, sut : system under test

    @Mock private ArticleRepository articleRepository; // 의존하는 모듈 불러오기

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // given
        // when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그
        // then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환")
    @Test
    void givenArticleId_whenSearchingArticles_thenReturnsArticle() {
        // given
        // when
        ArticleDto articles = sut.searchArticle(1L); // 제목, 본문, ID, 닉네임, 해시태그
        // then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성 ")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // given
        given(articleRepository.save(any(Article.class))).willReturn(null);  // save 시도

        // when
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Meta", "title", "content", "#java"));

        // then
        then(articleRepository).should().save(any(Article.class)); // save 수행 여부 점검

    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정 ")
    @Test
    void givenArticleInfoAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // given
        given(articleRepository.save(any(Article.class))).willReturn(null);  // save 시도

        // when
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

        // then
        then(articleRepository).should().save(any(Article.class)); // save 수행 여부 점검

    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제 ")
    @Test
    void givenArticleID_whenDeletingArticle_thenDeletesArticle() {
        // given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // when
        sut.deleteArticle(1L);

        // then
        then(articleRepository).should().delete(any(Article.class)); // delete 수행 여부 점검

    }

}