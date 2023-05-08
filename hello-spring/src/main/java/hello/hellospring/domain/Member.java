package hello.hellospring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

@Entity
public class Member {

    @jakarta.persistence.Id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    private String name; // 이름

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;

}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
