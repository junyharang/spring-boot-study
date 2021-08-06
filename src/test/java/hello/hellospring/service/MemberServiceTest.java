package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    // Dependency Injection (DI 기법)
    public void beforEach() {   // test를 실행하기 전에 MemoryMemberRepository 객체를 만들어 준 뒤 그 객체를 MemberService 객체를 생성 해 주면서 넣어준다.
        memberRepository = new MemoryMemberRepository();
        // 이렇게 해줌으로 MemberService와 MemberServiceTest가 같은 객체의 MemoryMemberRepository를 사용하게 된다.
        memberService = new MemberService(memberRepository);
    } // beforEach() 끝

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    } // afterEach() 끝

    @Test
    void 회원가입() {

        // given(무엇인가 주어졌을 때)
        Member member = new Member();
        member.setName("JunyHarang");

        // when(이것을 실행 했을 때)
        Long saveId = memberService.join(member);

        // then(결과가 아래와 같이 나와야 한다. (검증부))
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    } // 회원가입 () 끝

    @Test
    public void 중복회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("JunyHarang");

        Member member2 = new Member();
        member2.setName("JunyHarang");

        // when
        memberService.join(member1);

//        try {             // 방법 1
//            memberService.join(member2);
//            fail("예외가 발생해야 하는데, 발생하지 않았습니다.");
//
//        } catch (IllegalStateException e) {
//            // MemberService의 Throw 부분에 Exception 발생 시 출력하고자 했던 문구와 동일하게 isEqualTo에 넣는다.
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//            System.out.println("회원 가입 이름 중복으로 인해 예외 처리가 발생하였습니다.");
//        }

        // 방법 2
        // memberService.join()에 member2를 전달하여 로직을 실행할 건데, IllegalStateException가 발생해야 정상
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

        //then
    }

    @Test
    void findMembers() {
    } // findMembers() 끝

    @Test
    void findOne() {
    } // findOne() 끝
}