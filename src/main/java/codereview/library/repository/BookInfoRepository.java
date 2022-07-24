package codereview.library.repository;

import codereview.library.controller.BookInfoForm;
import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<BookInfo> findByString(BookInfoForm bookInfoForm) {
        String jpql = "select bi from BookInfo bi join bi.category c";
        boolean isFirstCondition = true;

        //저자 검색
        if (StringUtils.hasText(bookInfoForm.getIsbn())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " bi.isbn = :isbn";
        }

        //도서 분류 검색
        if (bookInfoForm.getCategory() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " c.name = :name";
        }

        //저자 검색
        if (StringUtils.hasText(bookInfoForm.getAuthor())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " bi.author = :author";
        }

        //도서명 검색
        if (StringUtils.hasText(bookInfoForm.getName())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " bi.name = :name";
        }

        //출판사 검색
        if (StringUtils.hasText(bookInfoForm.getPublisher())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " bi.publisher = :publisher";
        }

        TypedQuery<BookInfo> query = em.createQuery(jpql, BookInfo.class).setMaxResults(1000);    //최대 1000건

        if (StringUtils.hasText(bookInfoForm.getIsbn())) {
            query = query.setParameter("isbn", bookInfoForm.getIsbn());
        }
        if (bookInfoForm.getCategory() != null) {
            query = query.setParameter("category", bookInfoForm.getCategory().getName());
        }
        if (StringUtils.hasText(bookInfoForm.getAuthor())) {
            query = query.setParameter("author", bookInfoForm.getAuthor());
        }
        if (StringUtils.hasText(bookInfoForm.getName())) {
            query = query.setParameter("name", bookInfoForm.getName());
        }
        if (StringUtils.hasText(bookInfoForm.getPublisher())) {
            query = query.setParameter("publisher", bookInfoForm.getPublisher());
        }

        return query.getResultList();
    }
}
