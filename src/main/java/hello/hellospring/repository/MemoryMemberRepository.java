package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 실무에선 동시성을 고려해서 concurrentHashMap 사용
    private static long sequence = 0L; // id key값

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(), member); // id 추가
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null은 Optional.ofNullable로 감싸서 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // Lamda Loop 실행
                .filter(member -> member.getName().equals(name)) // 받아온 name이 같은지
                .findAny(); // 돌려서 있으면 반환, 끝까지 돌렸는데 없으면 Optiona로 인 null 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
