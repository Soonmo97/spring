package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** 통합테스트 */
// 스프링 서버를 띄우고 DB까지 연동한 테스트
// 가급적 단위테스트로 잘 만드는 것이 좋은 테스트이지만 필요할 때는 통합테스트로 작성.

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional // 테스트가 끝나면 롤백을 해서 DB 데이터가 깔끔하게 지워지기 때문에 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();  // shift + F6 -> rename 단축키
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
 /*
            try {
                memberService.join(member2);
                fail();
            } catch (IllegalStateException e) {
                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
*/
        //then
    }

}