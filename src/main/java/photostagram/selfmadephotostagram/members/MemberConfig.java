package photostagram.selfmadephotostagram.members;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import photostagram.selfmadephotostagram.members.repository.JdbcMemberRepository;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;

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
}
