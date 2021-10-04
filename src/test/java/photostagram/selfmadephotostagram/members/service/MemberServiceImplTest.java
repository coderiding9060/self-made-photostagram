package photostagram.selfmadephotostagram.members.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import photostagram.selfmadephotostagram.members.domain.Member;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void selectMemberById() {
        Optional<Member> member = memberRepository.selectMemberById("admin");
        /*Optional<Member> member = memberRepository.selectMemberById("admin1");*/
        if(!member.isEmpty()){
            System.out.println(member.get());
        } else {
            System.out.println("ID 조회 실패");
        }
    }

    @Test
    void selectMemberByEmail() {
        Optional<Member> member = memberRepository.selectMemberByEmail("admin@admin.com");
        /*Optional<Member> member = memberRepository.selectMemberByEmail("admin1@admin.com");*/
        if(!member.isEmpty()){
            System.out.println(member.get());
        } else {
            System.out.println("E-Mail 조회 실패");
        }
    }

    @Test
    void logInMember() {
        Optional<Member> member = memberRepository.logInMember("admin", "1234");
        /*Optional<Member> member = memberRepository.logInMember("admin1", "1234");*/
        if(!member.isEmpty()){
            System.out.println(member.get());
        } else {
            System.out.println("로그인 실패");
        }
    }

    @Test
    void selectMemberList() {
        List<Member> memberList = memberRepository.selectMemberList();
        for (Member member:memberList) {
            System.out.println("member = " + member);
        }
    }

    @Test
    void insertMember() {
        Member member = new Member();
        member.setId("member1");
        member.setPw("1234");
        member.setName("회원1");
        member.setEmail("member1@abc.com");
        member.setGender("여");
        Optional<Member> member1 = memberRepository.insertMember(member);
        if(!member1.isEmpty()){
            System.out.println(member1.get());
        } else {
            System.out.println("회원 가입 실패");
        }
    }

    @Test
    void updateMember() {
        Member member = new Member();
        member.setId("member1");
        member.setPw("1234");
        member.setName("회원1");
        member.setEmail("member1@abc.com");
        member.setGender("여");
        Optional<Member> member1 = memberRepository.insertMember(member);
        member1.get().setId("member1");
        member1.get().setPw("12345");
        member1.get().setName("회원1");
        member1.get().setEmail("member1@abc.com");
        member1.get().setGender("남");
        int updateResult = memberRepository.updateMember(member1.get());
        System.out.println("updateResult = " + updateResult);

    }

    @Test
    void deleteMember() {
        int deleteResult = memberRepository.deleteMember("member1");
        System.out.println("deleteResult = " + deleteResult);
    }
}