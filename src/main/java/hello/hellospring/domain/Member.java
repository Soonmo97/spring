package hello.hellospring.domain;


import javax.persistence.*;

@Entity // JPA가 관리하는 엔터티, ORM(Object Relational Mapping)
public class Member {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id값을 자동으로 생성 IDENTITY
    private Long id;

    //@Column(name = "username") <- DB 컬럼이 username일 경우 mapping
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
