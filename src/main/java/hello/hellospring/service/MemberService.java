package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 컴포넌트 스캔 방식의 Service
//@Service
@Transactional
public class MemberService {   // Service 쪽은 비즈니스 용어에 맞게 이름 등을 적어야 한다.
        // 아래 내용에서 repository 객체를 생성하고, Service Test에서 또 new를 통해 Repository를 생성해서 테스트하면 서로 다른 객체를 이용하게 된다.
//  private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 이 문제를 해결하기 위해서 아래와 같이 생성자를 통해 외부에서 접근할 수 있도록 해 준다.
    private final MemberRepository memberRepository;

    // @Service가 있는 Service가 객체가 생성될 때, @Autowired가 붙은 생성자는 스프링이 컨테이너에서 빈으로 관리할 때, 생성자를 호출하는데, 이 때 MemberService를 넣어준다.
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 생성자 끝

    // 회원 가입
    public Long join(Member member) {

        long start = System.currentTimeMillis();    // Method 호출의 시작 시간을 측정하기 위한 변수 선언

       // 같은 이름이 있는 중복 회원 가입 방지
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(memeber -> { // ifPresent는 해당 값이 Null이 아니고, 어떤 값이 있으면 동작 (Option로 감싸면 사용 가능)
//            throw new IllegalStateException("이미 존재하는 회원 입니다.");
//        });

        // 위에 코드 보다 더 좋은 코드
//        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원 입니다.")
//        });

        // 위의 코드를 Method 화 하는 것이 좋다.
        // 해당 코드를 모두 선택하고, Command + Option + M을 눌러준다.
        try {
            validateDuplicationMember(member);      // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join Method 동작 시간은 " + timeMs + "ms 입니다.");
        }
    } // join() 끝

    private void validateDuplicationMember(Member member) {
        // 위에 코드 보다 더 좋은 코드
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
    } // validateDuplicationMember() 끝

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers Method 동작 시간은 " + timeMs + "ms 입니다.");
        } // try 문 끝

    } // findMembers() 끝

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    } // findOne() 끝

} // Class 끝
