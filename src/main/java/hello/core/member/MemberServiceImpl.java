package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 실제 할당하는 new MemoryMemberRepository가 구현체를 의존하는 문제
    // 추상화 & 구체화 모두 의존 => DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //생성자 주입
    //AppConfig에서 설정정보 입력안하면 AutoAppConfig로 어떻게 관리?
    //->@Autowired
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //for test
    public hello.core.member.MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
