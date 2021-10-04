package photostagram.selfmadephotostagram.members.service;

import photostagram.selfmadephotostagram.members.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<Member> selectMemberById(String id);

    Optional<Member> selectMemberByEmail(String email);

    Optional<Member> logInMember(String id, String pw);

    List<Member> selectMemberList();

    Optional<Member> insertMember(Member member);

    int updateMember(Member member);

    int deleteMember(String id);
}
