package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        @GetMapping("/members/new")
        public String createForm() {
                return "members/createMemberForm";
        }

        @PostMapping("/members/new")    // members/createMemberForm.html에서 form으로 감싼 것을 post로 받음
                                          // 데이터를 전달 및 등록할 때는 form을 사용한 post 방식,
                                          // 데이터를 조회할 때는 url에 바로 입력하는 get 방식을 주로 쓴다.
        public String create(Member form) {
                Member member = new Member();
                member.setName(form.getName());

                memberService.join(member);

                return "redirect:/";
        }

        @GetMapping("/members")
        public String list(Model model) {
                List<Member> members = memberService.findMembers();
                model.addAttribute("members", members);
                return "members/memberList";
        }
}
