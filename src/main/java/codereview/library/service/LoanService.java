package codereview.library.service;

import codereview.library.domain.Loan;
import codereview.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    /**
     * 도서 대출
     */
    public void borrowBook(Loan loan) {
        loanRepository.borrow(loan);
    }

    /**
     * 도서 반납
     */
    public void returnBook(Loan loan) {
        loanRepository.returnBook(loan);
    }

}
