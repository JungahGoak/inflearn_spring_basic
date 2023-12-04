package hello.core.order;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 해결방안: 클라이언트의 OrderServiceImpl에 DiscountPolicy와 구현객체를 대신 생성하고 주입해주어야함

    //필드 주입: 의존관계를 필드에 바로 주입
    //외부에서 변경이 불가능하여 테스트하기 힘듦(순수 java로 테스트 불가)
    //사용하지 말자!-> 애플리케이션의 실제 코드와 관계없는 테스트 코드
    @Autowired  private DiscountPolicy discountPolicy;
    @Autowired  private MemberRepository memberRepository;

    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
//
//    //생성자 주입: 생성자 호출시점에서 딱 1번만 호출되는 것이 보장, 불변 필수 의존관계에서 사용
//    //@Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
//        this.discountPolicy = discountPolicy;
//        this.memberRepository = memberRepository;
//    }

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
