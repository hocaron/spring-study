package hocaron.basic.discount;

import hocaron.basic.member.Grade;
import hocaron.basic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixMount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixMount;
        }else{
            return 0;
        }
    }
}