package likelion.PostSiteVer1.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
//import org.springframework.data.annotation.Id;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name="createdat")
    private LocalDateTime createddate;

    @Builder
    public Post(String title, String description) {
        this.title=title;
        this.description= description;
        this.createddate=createddate.now();
    }

    //편의 메서드
    public void update(String title, String description) {
        this.title=title;
        this.description = description;
        this.createddate=createddate.now();
    }

}