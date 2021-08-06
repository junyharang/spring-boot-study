package hello.hellospring;

import hello.hellospring.repository.MemeberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfing {

    // @Bean : Bean을 내가 직접 만들겠다는 의미; 스프링은 이것을 보고, Bean을 등록하라는 명령으로 인식
    @Bean
    // 아래 Method를 호출하여 Bean에 등록
    public MemberService memberService() {
        // MeberService를 생성하면서 memberRepository를 Spring Bean에 등록 된 주입하여 준다.
        return new MemberService((MemoryMemberRepository) memberRepository());
    } // memberService() 끝

    @Bean
    public MemeberRepository memberRepository() {
        return new MemoryMemberRepository();
    } // memberRepository() 끝
} // Class 끝
