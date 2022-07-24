package codereview.library.controller;

import codereview.library.domain.Book;
import codereview.library.domain.LoanStatus;
import codereview.library.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanForm {

    private Long id;
    private Member member;
    private Book book;
    private LoanStatus loanStatus;
}
