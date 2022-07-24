package codereview.library.controller;

import codereview.library.domain.*;
import codereview.library.service.BookService;
import codereview.library.service.MemberService;
import codereview.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/reservation/new")
    public String createForm(Model model) {
        List<Member> memberList = memberService.findMembers();
        List<Book> bookList = bookService.findBooks();

        model.addAttribute("memberList", memberList);
        model.addAttribute("bookList", bookList);
        model.addAttribute("reservationForm", new ReservationForm());
        return "reservations/createReservationForm";
    }

    @PostMapping("/reservation/new")
    public String create(@RequestParam("memberId") Long memberId,
                         @RequestParam("bookId") Long bookId,
                         @Valid ReservationForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "reservations/createReservationForm";
        }

        Member member = memberService.findOne(memberId);
        Book book = bookService.findOne(bookId);
        Reservation reservation = new Reservation(member, book, LocalDateTime.now());
        reservationService.reserve(reservation);

        return "redirect:/";
    }

    @GetMapping("/reservation")
    public String list(@ModelAttribute("memberSearch") MemberSearch memberSearch, Model model) {

        List<Member> members = memberService.findMembers();
        List<Reservation> reservations = reservationService.findAllReservs();

        model.addAttribute("members", members);
        model.addAttribute("reservations", reservations);
        return "reservations/reservationList";
    }


    @GetMapping("/reservation/{reservationId}/delete")
    public String delete(@PathVariable("reservationId") Long reservationId) {

        Reservation findReservation = reservationService.findOne(reservationId);

        reservationService.deleteReserv(findReservation);

        return "redirect:/reservation";
    }
}
