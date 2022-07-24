package codereview.library.controller;

import codereview.library.domain.Address;
import codereview.library.domain.Member;
import codereview.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getZipcode(), form.getMain_address(), form.getSub_address());
        Member member = new Member(form.getName(), form.getBirth(), form.getPhone(), form.getEmail(), address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/{memberId}/update")
    public String updateMemberForm(@PathVariable("memberId") Long memberId, Model model) {

        Member member = memberService.findOne(memberId);

        MemberForm form = new MemberForm();
        form.setPhone(member.getPhone());
        form.setEmail(member.getEmail());
        form.setZipcode(member.getAddress().getZipcode());
        form.setMain_address(member.getAddress().getMain_address());
        form.setSub_address(member.getAddress().getSub_address());

        model.addAttribute("form", form);
        return "members/updateMemberForm";
    }

    @PostMapping("/members/{memberId}/update")
    public String updateMember(@ModelAttribute("form") MemberForm form) {

        memberService.updateMember(form.getId(), form.getPhone(), form.getEmail(), form.getZipcode(), form.getMain_address(), form.getSub_address());
        return "redirect:/members";
    }

    @GetMapping("/members/{memberId}/delete")
    public String delete(@PathVariable("memberId") Long memberId) {

        Member findMember = memberService.findOne(memberId);

        memberService.deleteMember(findMember);

        return "redirect:/members";
    }
}
