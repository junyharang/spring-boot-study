package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컴포넌트 스캔 방식의 Controller
@Controller     // 이 어노테이션을 스프링이 보고, 해당 Controller Class를 객체로 만든 뒤 스프링이 가지고 있는다.
// 그것을 스프링 컨테이너에서 빈으로 관리 된다고 표현한다.
public class MemberController {
        // 아래와 같이 MemberService를 new를 통해 생성하게 되면 다른 Controller에서도 접근이 가능한 문제
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // Dependency Injection (DI)
    // @controller가 있는 Controller 객체가 생성될 때, @Autowired가 붙은 생성자는 스프링이 컨테이너에서 빈으로 관리할 때, 생성자를 호출하는데, 이 때 MemberService를 넣어준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // 생성자 () 끝

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    } // createForm() 끝

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원 가입이 끝나면 home화면으로 보내기
        return "redirect:/";
    } // create() 끝


} // Class () 끝
