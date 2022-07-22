package codereview.library.repository;

import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.domain.BookStatus;
import codereview.library.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Book book) {
        em.persist(book);
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    public List<Book> findByIsbn(BookInfo bookInfo) {

        String jpql = "select b from Book b join b.bookInfo bi";
        boolean isFirstCondition = true;

        if (bookInfo.getIsbn() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " bi.isbn = :isbn";
        }

        TypedQuery<Book> query = em.createQuery(jpql, Book.class)
                .setMaxResults(1000);

        if (bookInfo.getIsbn() != null) {
            query = query.setParameter("isbn", bookInfo.getIsbn());
        }

        return query.getResultList();
    }

    public List<Book> findByStatus(BookStatus bookStatus) {
        return em.createQuery("select b from Book b where b.bookStatus = :bookStatus", Book.class)
                .setParameter("bookStatus", bookStatus)
                .getResultList();
    }

    public void delete(Book book) {
        em.remove(book);
    }

}
