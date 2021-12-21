package hocaron.basic;

import hocaron.basic.discount.DiscountPolicy;
import hocaron.basic.discount.RateDiscountPolicy;
import hocaron.basic.member.MemberRepository;
import hocaron.basic.member.MemberService;
import hocaron.basic.member.MemberServiceImpl;
import hocaron.basic.member.MemoryMemberRepository;
import hocaron.basic.order.OrderService;
import hocaron.basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
