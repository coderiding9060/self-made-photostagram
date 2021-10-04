package photostagram.selfmadephotostagram.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import photostagram.selfmadephotostagram.members.repository.JdbcMemberRepository;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;
import photostagram.selfmadephotostagram.members.service.MemberService;
import photostagram.selfmadephotostagram.members.service.MemberServiceImpl;
import photostagram.selfmadephotostagram.members.util.SessionManageUtil;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
public class MemberConfig {

    private DataSource dataSource;

    private HttpSession httpSession;

    @Autowired
    public MemberConfig(DataSource dataSource, HttpSession httpSession) {
        this.dataSource = dataSource;
        this.httpSession = httpSession;
    }

    @Bean
    MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    SessionManageUtil sessionManageUtil(){
        return new SessionManageUtil(httpSession);
    }
}
