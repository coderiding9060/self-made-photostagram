package photostagram.selfmadephotostagram.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photostagram.selfmadephotostagram.members.domain.Member;
import photostagram.selfmadephotostagram.members.repository.MemberRepository;
import photostagram.selfmadephotostagram.members.service.MemberService;
import photostagram.selfmadephotostagram.members.util.SessionManageUtil;

import java.util.Optional;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SessionManageUtil sessionManageUtil;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    // 회원 가입 페이지로 이동
    @GetMapping(value = "/joinForm.me")
    public String joinForm(){
        return "members/insertForm";
    }

    // 회원 정보 조회 페이지로 이동 & 회원 정보 조회
    @GetMapping(value = "/info.me")
    public String getMemberInfo(@RequestParam(name="id") String id, Model model) {
        Optional<Member> member = memberRepository.selectMemberById(id);
        if (!member.isEmpty()) {
            System.out.println("member.get() = " + member.get());
        }
        model.addAttribute("member", member.get());
        return "members/info";
    }

    // 로그인 페이지로 이동
    @GetMapping(value = "/logInForm.me")
    public String logInForm(){
        return "members/logInForm";
    }

    // 로그인 처리
    @GetMapping(value = "/logIn.me")
    public String logInMember(@RequestParam(name = "id") String id, @RequestParam(name = "pw") String pw) {
        Optional<Member> member = memberRepository.logInMember(id, pw);
        if(!member.isEmpty()){
            String memberId = sessionManageUtil.createSession("memberId", member.get().getId());
            if(memberId.length()>0){
                System.out.println("로그인 성공");
                return "redirect:/";
            }
        }
        return null;
    }




}
