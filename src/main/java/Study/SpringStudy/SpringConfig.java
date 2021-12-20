package Study.SpringStudy;

import Study.SpringStudy.repository.*;
import Study.SpringStudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }
}