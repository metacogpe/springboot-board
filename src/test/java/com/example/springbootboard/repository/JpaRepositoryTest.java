package com.example.springbootboard.repository;

import com.example.springbootboard.config.JpaConfig;
import com.example.springbootboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;  // Jnuit5 대신에 assertj를 더 선호

@DisplayName("JPA 연결 테스")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);

    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {

        // given
        long previousCount = articleRepository.count();
        Article article = Article.of("new article", "new content", "#hashtag");

        // when
        Article savedArticle = articleRepository.save(article);
        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        // given : 첫 번째 article을 찾아서, #Springboot로 셋팅
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#Springboot";
        article.setHashtag(updateHashtag);

        // when : 변경한 article 저장
        Article savedArticle = articleRepository.saveAndFlush(article);  // saveAndFlush : update 쿼리 확인 용도
        // then : 변경 저장된 아티클의 hashtag값이 업데이트한 hashtag값인지 확인
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);

    }
}