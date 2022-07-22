package codereview.library.repository;

import codereview.library.domain.Member;
import codereview.library.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findByPhone(Member member) {

        String jpql = "select r from Reservation r join r.member m";
        boolean isFirstCondition = true;

        if (member.getPhone() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.phone = :phone";
        }

        TypedQuery<Reservation> query = em.createQuery(jpql, Reservation.class)
                .setMaxResults(1000);

        if (member.getPhone() != null) {
            query = query.setParameter("phone", member.getPhone());
        }

        return query.getResultList();
    }

    public void delete(Reservation reservation) {
        em.remove(reservation);
    }
}
