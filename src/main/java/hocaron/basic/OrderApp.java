package hocaron.basic;

import hocaron.basic.member.Grade;
import hocaron.basic.member.Member;
import hocaron.basic.member.MemberService;
import hocaron.basic.member.MemberServiceImpl;
import hocaron.basic.order.Order;
import hocaron.basic.order.OrderService;
import hocaron.basic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("caluatePrice = " + order.calculatePrice());
    }
}
