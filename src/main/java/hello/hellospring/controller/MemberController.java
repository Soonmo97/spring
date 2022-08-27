package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨트롤러 어노테이션이 있으면 스프링이 시작될 때 객체화 되어 컨테이너에서 관리된다.
            // @Component 어노테이션이 있으면 스프링 빈으로 자동 등록된다. (스프링 빈으로 등록되어 사용하면 이점이 많다.)
            // @Controller, @Service, @Repository는 @Component를 포함하고 있다.
            // 따라서 위 어노테이션들이 있으면 스프링 빈으로 자동 등록된다.
            // 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다(유일하게 하나만 등록해서 공유한다)
            // 따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만,
            // 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.


public class MemberController {

        // private final MemberService memberService = new MemberService();
        // 위처럼 작성하면 MemberService 하나만 생성하면 되는데 사용할 때마다 생성해야함 따라서 아래처럼 작성
        private final MemberService memberService;// spring 컨테이너에 하나만 등록하면 공용으로 사용할 수 있다.
                                                  // cmd + n 사용해서 Constructor

        @Autowired  // 컨테이너에서 MemberService 가져옴.
        public MemberController(MemberService memberService) {
                this.memberService = memberService;
        }
}
