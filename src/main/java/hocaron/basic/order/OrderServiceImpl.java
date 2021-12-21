package hocaron.basic.order;

import hocaron.basic.discount.DiscountPolicy;
import hocaron.basic.discount.FixDiscountPolicy;
import hocaron.basic.member.Member;
import hocaron.basic.member.MemberRepository;
import hocaron.basic.member.MemberService;
import hocaron.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
