package codereview.library.repository;

import codereview.library.domain.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class LoanRepository {

    @PersistenceContext
    private EntityManager em;

    public void borrow(Loan loan) {
        em.persist(loan);
    }

    public void returnBook(Loan loan) {
        em.remove(loan);
    }
}
