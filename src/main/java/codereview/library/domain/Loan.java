package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Loan {

    @Id @GeneratedValue
    @Column(name = "loan_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus loanStatus;

    @Column(nullable = false)
    private LocalDateTime loanedDate;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    protected Loan() {
    }

    public Loan(Member member, Book book, LocalDateTime loanedDate, LocalDateTime returnDate) {
        this.member = member;
        this.book = book;
        this.loanedDate = loanedDate;
        this.returnDate = returnDate;
    }

    public void changeToLoan() {
        this.loanStatus = LoanStatus.LOAN;
    }

    public void changeToReturn() {
        this.loanStatus = LoanStatus.RETURN;
    }
}
