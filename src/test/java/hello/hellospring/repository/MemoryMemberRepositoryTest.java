package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // Test가 하나 끝날 때마다 Repository를 Clear 시켜주기 위한 코드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {    // MemoryMemberRepository의 save 메소드를 가지고 Test
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // member안의 값과 result안의 값이 같은지 비교
        //Assertions.assertEquals(member, result);  예전 기법

        Assertions.assertThat(member).isEqualTo(result);

        System.out.println("save TEST의 result 값은 " + (member == result) + " 입니다.");
    }// save() 끝

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        System.out.println("findByName TEST의 result 값은 " + (result == member1) + " 입니다.");

        Assertions.assertThat(result).isEqualTo(member1);
    } // findByName() 끝

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        System.out.println("findByALL TEST의 result 크기는 " + result.size() + " 입니다.");

        Assertions.assertThat(result.size()).isEqualTo(2);
    } // findAll() 끝
}// TEST Class () 끝