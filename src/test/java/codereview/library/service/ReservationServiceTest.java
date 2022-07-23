package codereview.library.service;

import codereview.library.domain.*;
import codereview.library.repository.BookRepository;
import codereview.library.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookInfoService bookInfoService;

    @Test
    public void 예약등록() throws Exception {

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

        Reservation reservation = new Reservation(member, book, LocalDateTime.now());

        //when
        Long reservationId = reservationService.reserve(reservation);

        //then
        Assertions.assertEquals(reservation, reservationRepository.findOne(reservationId));
    }
    
    @Test
    public void 전체예약조회() throws Exception {
        
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

        Reservation reservation = new Reservation(member, book, LocalDateTime.now());
        Long reservationId = reservationService.reserve(reservation); 
        
        //when
        List<Reservation> reservations = reservationService.findAllReservs();
        
        //then
        for (Reservation reservation1 : reservations) {
            System.out.println("reservation1.getMember() = " + reservation1.getMember());
        }
    }

    @Test
    public void 전화번호로예약조회() throws Exception {

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

        Reservation reservation = new Reservation(member, book, LocalDateTime.now());
        Long reservationId = reservationService.reserve(reservation);

        //when
        List<Reservation> reservations = reservationService.findReservByPhone(reservation);

        //then
        for (Reservation reservation1 : reservations) {
            System.out.println("reservation1.getMember() = " + reservation1.getMember());
        }
    }

    @Test
    public void 예약삭제() throws Exception {

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

        Reservation reservation = new Reservation(member, book, LocalDateTime.now());
        Long reservationId = reservationService.reserve(reservation);

        //when
        reservationService.deleteReserv(reservation);

        //then
        List<Reservation> reservations = reservationService.findAllReservs();
        for (Reservation reservation1 : reservations) {
            System.out.println("reservation1.getMember() = " + reservation1.getMember());
        }
    }
}
