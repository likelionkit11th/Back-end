package hello.hellospring.domain;

public class Member {
    private Long id; // 고유번호
    private String name; // 이름

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
