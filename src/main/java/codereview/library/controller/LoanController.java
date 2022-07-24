package codereview.library.controller;

import codereview.library.domain.*;
import codereview.library.service.BookService;
import codereview.library.service.LoanService;
import codereview.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/loan/new")
    public String createLoanForm(Model model) {

        List<Member> memberList = memberService.findMembers();
        List<Book> bookList = bookService.findBooks();

        model.addAttribute("memberList", memberList);
        model.addAttribute("bookList", bookList);
        model.addAttribute("loanForm", new LoanForm());
        return "loan/createLoanForm";
    }

    @PostMapping("/loan/new")
    public String createLoan(@RequestParam("memberId") Long memberId,
                             @RequestParam("bookId") Long bookId,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "loan/createLoanForm";
        }

        Member member = memberService.findOne(memberId);
        Book book = bookService.findOne(bookId);
        Loan loan = new Loan(member, book, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        loanService.borrowBook(loan);

        return "redirect:/";
    }

//    @GetMapping("/loan/{loanId}/return")
//    public String createReturnForm(@PathVariable("loanId") Long loanId, Model model) {
//
//        List<Member> memberList = memberService.findMembers();
//        List<Book> bookList = bookService.findBooks();
//
//        model.addAttribute("memberList", memberList);
//        model.addAttribute("bookList", bookList);
//        model.addAttribute("loanForm", new BookForm());
//        return "loan/createReturnForm";
//    }
//
//    @PostMapping("/loan/{loanId}/return")
//    public String createReturn(@PathVariable("loanId") Long loanId,
//                               @RequestParam("memberId") Long memberId,
//                               @RequestParam("bookId") Long bookId,
//                               BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "loan/createReturnForm";
//        }
//
//        Member member = memberService.findOne(memberId);
//        Book book = bookService.findOne(bookId);
//        Loan loan = new Loan(member, book, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
//        loanService.returnBook(loan.getId());
//
//        return "redirect:/";
//    }
}
