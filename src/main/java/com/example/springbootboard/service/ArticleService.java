package com.example.springbootboard.service;

import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor   // 필수 필드에 대한 생성자 생성
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleDto> searchArticles(SearchType title, String search_keyword) {
        return List.of();

    }
}
