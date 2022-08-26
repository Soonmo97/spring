package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 등록(추가)
    Optional<Member> findById(Long id); // 회원 key, Optional은 null을 반환하지 않도록 하는 것 (Java 8~)
    Optional<Member> findByName(String name); // 회원 이름
    List<Member> findAll(); // 회원 리스트
}
