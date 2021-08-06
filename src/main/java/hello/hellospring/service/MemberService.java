package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {   // Service 쪽은 비즈니스 용어에 맞게 이름 등을 적어야 한다.

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    public Long join(Member member) {
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

        // 위의 코드를 Method화 하는 것이 좋다.
        // 해당 코드를 모두 선택하고, Command + Option + M을 눌러준다.

        validateDuplicationMember(member);      // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    } // join() 끝

    private void validateDuplicationMember(Member member) {
        // 위에 코드 보다 더 좋은 코드
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
    } // validateDuplicationMember() 끝

    public List<Member> findMembers() {
        return memberRepository.findAll();
    } // findMembers() 끝

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    } // findOne() 끝

} // Class 끝
