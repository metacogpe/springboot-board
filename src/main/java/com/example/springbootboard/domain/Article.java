package com.example.springbootboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment 설정 : IDENTITY 방식으로 설정
    private Long id;   // id는 setter에 의한 것이 아닌 자동으로 셋팅됨

    // Setter는 각 필드에 걸기 : 클래스 레벨에서 걸지 않기, 특정 필드만 셋팅 가능하게 하려는 의도임
    @Setter private String title;
    @Setter private String content;

    @Setter private String hashtag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
