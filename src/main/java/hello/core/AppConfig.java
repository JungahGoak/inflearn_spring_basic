package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 객체의 생성과 연결 담당
// AppConfig는 애플리케이션  실제 동작에 필요한 구현 객체를 생성
@Configuration
public class AppConfig {


    // AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입
    // => 생성자 주입
    @Bean
    public MemberService memberService() {
        
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), getDiscountPolicy());
    }

    @Bean
    private static DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
