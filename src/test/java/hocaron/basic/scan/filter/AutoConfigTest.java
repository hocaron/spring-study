package hocaron.basic.scan.filter;

import hocaron.basic.AutoAppConfig;
import hocaron.basic.member.Member;
import hocaron.basic.member.MemberRepository;
import hocaron.basic.member.MemberService;
import hocaron.basic.order.Order;
import hocaron.basic.order.OrderService;
import hocaron.basic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println(memberRepository);
    }
}
