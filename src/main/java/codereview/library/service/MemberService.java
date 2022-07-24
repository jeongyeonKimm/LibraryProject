package codereview.library.service;

import codereview.library.domain.Address;
import codereview.library.domain.Member;
import codereview.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 아이디로 회원 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 이름으로 회원 조회
     */
    public List<Member> findMemberByName(Member member) {
        return memberRepository.findByName(member.getName());
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void updateMember(Long id, String phone, String email, String zipcode, String main_address, String sub_address) {
        Member findMember = memberRepository.findOne(id);

        findMember.changePhone(phone);
        findMember.changeEmail(email);
        findMember.changeAddress(zipcode, main_address, sub_address);

    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException(("이미 존재하는 회원입니다."));
        }

    }
}
