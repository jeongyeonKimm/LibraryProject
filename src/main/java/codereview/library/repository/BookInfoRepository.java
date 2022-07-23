package codereview.library.repository;

import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookInfoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(BookInfo bookInfo) {
        em.persist(bookInfo);
    }

    public List<BookInfo> findAll() {
        return em.createQuery("select bi from BookInfo bi", BookInfo.class)
                .getResultList();
    }

    public BookInfo findByIsbn(String isbn) {
        return em.find(BookInfo.class, isbn);
    }

    public List<BookInfo> findByCategory(Category category) {
        return em.createQuery("select bi from BookInfo bi where bi.category = :category")
                .setParameter("category", category)
                .getResultList();
    }

    public List<BookInfo> findByName(String name) {
        return em.createQuery("select bi from BookInfo bi where bi.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

    public List<BookInfo> findByPublisher(String publisher) {
        return em.createQuery("select bi from BookInfo bi where bi.publisher = :publisher")
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<BookInfo> findByAuthor(String author) {
        return em.createQuery("select bi from BookInfo bi where bi.author = :author")
                .setParameter("author", author)
                .getResultList();
    }

    public void delete(BookInfo bookInfo) {
        em.remove(bookInfo);
    }
}
