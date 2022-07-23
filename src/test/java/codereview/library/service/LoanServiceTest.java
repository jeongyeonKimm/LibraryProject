package codereview.library.service;

import codereview.library.domain.*;
import codereview.library.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static codereview.library.domain.LoanStatus.RETURN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoanServiceTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookService bookService;

    @Test
    public void 도서대출() throws Exception {

        //given
        Member member = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        Long memberId = memberService.join(member);

        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn = bookInfoService.register(bookInfo);

        Book book = new Book(bookInfo, BookStatus.RESERVATION);
        Long bookId = bookService.register(book);

        Loan loan = new Loan(member, book, LocalDateTime.now(), LocalDateTime.now());

        //when
        Long loanId = loanService.borrowBook(loan);

        //then
        assertEquals(loan, loanRepository.findOne(loanId));
    }
    
    @Test
    public void 도서반납() throws Exception {

        //given
        Member member = new Member("member1", "1999-10-06", "123-123",
                "member1@gmail.com", new Address("12345", "Seoul", "Gwangjin"));
        Long memberId = memberService.join(member);

        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn = bookInfoService.register(bookInfo);

        Book book = new Book(bookInfo, BookStatus.RESERVATION);
        Long bookId = bookService.register(book);

        Loan loan = new Loan(member, book, LocalDateTime.now(), LocalDateTime.now());
        Long loanId = loanService.borrowBook(loan);
        
        //when
        loanService.returnBook(loan);

        //then
        System.out.println("loan.getLoanStatus() = " + loan.getLoanStatus());
    }
}