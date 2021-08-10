package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional      // DB에 Data를 저장한 뒤 Spring이 Test를 한 뒤에 DATA를 지워(RollBack)주기 위해 사용
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // @commit   이것을 사용하면 DB에서 RollBack이 아닌 commit을 해 버려서 Data를 입력해 버린다.
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
} // Class 끝