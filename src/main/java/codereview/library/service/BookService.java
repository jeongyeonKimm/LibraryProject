package codereview.library.service;

import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * 도서 등록
     */
    @Transactional
    public Long register(Book book) {

        bookRepository.save(book);
        return book.getId();
    }

    /**
     * 전체 도서 조회
     */
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    /**
     * isbn으로 도서 조회
     */
    public List<Book> findBookByIsbn(Book book) {
        return bookRepository.findByIsbn(book.getBookInfo());
    }

    /**
     * 예약여부로 도서 조회
     */
    public List<Book> findBookByStatus(Book book) {
        return bookRepository.findByStatus(book.getBookStatus());
    }

    /**
     * 도서 삭제
     */
    @Transactional
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

}
