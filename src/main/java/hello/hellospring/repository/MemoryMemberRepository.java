package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemeberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    } //save () 끝

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    } // findById() 끝

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    } // findByName () 끝

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    } // findAll() 끝

    // Test가 하나 끝날 때마다 Repository를 Clear 시켜주기 위한 코드 (MemoryMemberRepositoryTest의 afterEach()에서 호출)
    public void clearStore() {
        store.clear();
    }
} // Class 끝
