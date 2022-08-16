package com.example.springbootboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data REST - API Test")
@Transactional  // DB에 영향을 주는 테스트이므로 어노테이션 추가 : 기본 동작은 롤백이 됨 (트랜잭션이 열리고, 닫히게 됨)
//@WebMvcTest     // 컨트롤러에 대한 테스트이므로 MVC 테스트를 사용하기 위해 연결 -> 하지만 아래의 @SpringBootTest
@SpringBootTest   // WebMvcTest(컨트롤러 외에 로드하지 않음 : Data REST auto config 로드 않음)로는 실행할 수 없어 통합 테스트로 변경 처리
@AutoConfigureMockMvc  // @SpringBootTest만으로는 MockMvc를 알 수 없어 추가
public class DataRestTest {

    private final MockMvc mvc;    // MockMvc를 사용할 수 있게 Bean을 준비

    public DataRestTest(@Autowired MockMvc mvc) {   // 생성자 방식으로 주입
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] 게시글 게시글 -> 댓글 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }
}
