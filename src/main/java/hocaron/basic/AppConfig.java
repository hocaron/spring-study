package hocaron.basic;

import hocaron.basic.discount.FixDiscountPolicy;
import hocaron.basic.member.MemberService;
import hocaron.basic.member.MemberServiceImpl;
import hocaron.basic.member.MemoryMemberRepository;
import hocaron.basic.order.OrderService;
import hocaron.basic.order.OrderServiceImpl;

public class AppConfig {


    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
