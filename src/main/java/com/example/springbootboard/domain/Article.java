package com.example.springbootboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;

    @Setter private String hashtag;

    // 양방향 바인딩 : 실무에서는 강한 결합으로 권장하지 않음
    @ToString.Exclude    // ToString 순환참조 방지를 위해 여기서 끊음
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL ) // cascade 제약조건 적용
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();  // 댓글의 중복을 허용하지 않고, 모아서 보기 위함

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    
    protected Article() {}  // arg 없는 생성자 만들기 : 롬복으로도 가능

    public Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
}
