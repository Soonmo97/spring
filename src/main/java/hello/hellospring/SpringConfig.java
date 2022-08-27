package hello.hellospring;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        // return new DbMemberRepository(); -> MemoryMemberRepository()는 현재 가상의 시나리오로 인한 임시 저장소이기에
        // 추후에 DB가 연결된 Repository를 연결할 때 다른 코드를 건드릴 필요없이 이 파일에서 위 코드대로만 수정해주면 정상 작동하게 된다.
        // 이 것을 위해서 어노테이션으(컴포넌트) 의존 관계로 빈을 등록하지 않고 자바 코드로 직접 스프링 빈 등록을 한다.
    }
}
