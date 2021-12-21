package hocaron.basic.order;

import hocaron.basic.discount.DiscountPolicy;
import hocaron.basic.member.Member;
import hocaron.basic.member.MemberRepository;
import hocaron.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private  MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
