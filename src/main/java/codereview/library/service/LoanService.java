package codereview.library.service;

import codereview.library.domain.Loan;
import codereview.library.domain.Member;
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
    @Transactional
    public Long borrowBook(Loan loan) {
        loanRepository.borrow(loan);
        return loan.getId();
    }

    /**
     * 도서 반납
     */
    @Transactional
    public void returnBook(Long id) {

        Loan findLoan = loanRepository.findOne(id);

        findLoan.changeToReturn();
    }

}
