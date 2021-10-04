package photostagram.selfmadephotostagram.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import photostagram.selfmadephotostagram.members.domain.Member;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> selectMemberById(String id) {
        return memberRepository.selectMemberById(id);
    }

    @Override
    public Optional<Member> selectMemberByEmail(String email) {
        return memberRepository.selectMemberByEmail(email);
    }

    @Override
    public Optional<Member> logInMember(String id, String pw) {
        return memberRepository.logInMember(id,pw);
    }

    @Override
    public List<Member> selectMemberList() {
        return memberRepository.selectMemberList();
    }

    @Override
    public Optional<Member> insertMember(Member member) {
        return memberRepository.insertMember(member);
    }

    @Override
    public int updateMember(Member member) {
        return memberRepository.updateMember(member);
    }

    @Override
    public int deleteMember(String id) {
        return memberRepository.deleteMember(id);
    }
}
