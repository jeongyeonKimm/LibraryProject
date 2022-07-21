package codereview.library.service;

import codereview.library.domain.Address;
import codereview.library.domain.Member;
import codereview.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {

        //given
        Member member = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));

        //when
        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));
    }
}
