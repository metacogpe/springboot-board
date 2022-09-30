package com.example.springbootboard.service;

import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.assertj.core.api.Assertions.*;  // JUnit 대신 assertj 사용
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
        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그
        // then
        assertThat(articles).isNotNull();
    }


}