package Study.SpringStudy;

import Study.SpringStudy.repository.JdbcMemberRepository;
import Study.SpringStudy.repository.JdbcTempleteMemberRepository;
import Study.SpringStudy.repository.MemberRepository;
import Study.SpringStudy.repository.MemoryMemberRepository;
import Study.SpringStudy.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
        return new JdbcTempleteMemberRepository(dataSource);
    }
}