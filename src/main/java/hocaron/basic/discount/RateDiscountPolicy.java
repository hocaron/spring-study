package hocaron.basic.discount;

import hocaron.basic.member.Grade;
import hocaron.basic.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
