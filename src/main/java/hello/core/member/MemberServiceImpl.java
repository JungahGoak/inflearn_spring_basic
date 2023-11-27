package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 실제 할당하는 new MemoryMemberRepository가 구현체를 의존하는 문제
    // 추상화 & 구체화 모두 의존 => DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
