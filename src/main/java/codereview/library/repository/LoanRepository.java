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
        loan.changeToLoan();
        em.persist(loan);
    }

    public Loan findOne(Long id) {
        return em.find(Loan.class, id);
    }
}
