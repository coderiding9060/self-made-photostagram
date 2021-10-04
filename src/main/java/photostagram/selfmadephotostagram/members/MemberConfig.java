package photostagram.selfmadephotostagram.members;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import photostagram.selfmadephotostagram.members.repository.JdbcMemberRepository;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;
import photostagram.selfmadephotostagram.members.service.MemberService;
import photostagram.selfmadephotostagram.members.service.MemberServiceImpl;

import javax.sql.DataSource;

@Configuration
public class MemberConfig {

    private DataSource dataSource;

    public MemberConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
}
