package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository; //인터페이스 구현을 해준다.

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    //Optional<Member> findByNameAndId(String name, Long id); 이런 식으로 바꿔서 활용가능
}
