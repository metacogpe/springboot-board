package com.example.springbootboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View Controller - 게시글")
@WebMvcTest(ArticleController.class)  // 테스트 대상 컨트롤러만 빈으로 불러와서 테스트
class ArticleControllerTest {

    private final MockMvc mvc;    // MockMvc를 Autowired해서 사용 가능

    //생성자 주입방식으로 접근, Test패키지에서는 @Autowired 꼭 추가(생략 불가)
    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

//    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {

        // when & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())  // 상태 체크
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))  // 내용 타입 : 뷰라서 text html임
                .andExpect(view().name("articles/index"))  // 뷰의 이름 체크
                .andExpect(model().attributeExists("articles"));  // 데이터 있는지 여부까지 검사
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {

        // when & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())  // 상태 체크
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))  // 내용 타입 : 뷰라서 text html임
                .andExpect(view().name("article/detail"))  // 뷰의 이름 체크
                .andExpect(model().attributeExists("article")) // 데이터 있는지 여부까지 검사
                .andExpect(view().name("articleComments")); // 뷰의 이름 체크
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {

        // when & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())  // 상태 체크
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))  // 내용 타입 : 뷰라서 text html임
                .andExpect(view().name("articles/search"));  // 뷰의 이름 체크
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태크 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {

        // when & then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())  // 상태 체크
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))  // 내용 타입 : 뷰라서 text html임
                .andExpect(view().name("articles/search-hashtag"));  // 뷰의 이름 체크
    }

}