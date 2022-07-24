package codereview.library.service;

import codereview.library.domain.Address;
import codereview.library.domain.Member;
import codereview.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        //given
        Member member = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));

        //when
        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        //given
        Member member1 = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));

        Member member2 = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    public void 전체_회원_조회() throws Exception {

        //given
        Member member1 = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member1);

        Member member2 = new Member("member2", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member2);

        //when
        List<Member> findMembers = memberService.findMembers();

        //then
        for (Member member : findMembers) {
            System.out.println("member.getName() = " + member.getName());
        }
    }

    @Test
    public void 이름으로_회원_조회() throws Exception {

        //given
        Member member1 = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member1);

        Member member2 = new Member("member2", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member2);

        //when
        List<Member> findMembers = memberService.findMemberByName(member1);

        //then
        for (Member member : findMembers) {
            System.out.println("member.getId() = " + member.getId());
        }
    }

    @Test
    public void 회원수정() throws Exception {

        //given
        Member member = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        Long memberId = memberService.join(member);

        //when
        memberService.updateMember(memberId, "1234-1234", "member1@naver.com",
                "12345", "Seoul", "Gwangjin"); //phone, email 수정
    }

    @Test
    public void 회원삭제() throws Exception {

        //given
        Member member1 = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member1);

        Member member2 = new Member("member2", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        memberService.join(member2);

        //when
        memberService.deleteMember(member1);

        //then
        List<Member> findMembers = memberService.findMembers();

        for (Member member : findMembers) {
            System.out.println("member.getName() = " + member.getName());
        }
    }
}
