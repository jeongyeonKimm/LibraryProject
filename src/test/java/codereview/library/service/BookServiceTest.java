package codereview.library.service;

import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.domain.BookStatus;
import codereview.library.domain.Category;
import codereview.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookInfoService bookInfoService;

    @Test
    public void 도서등록() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn = bookInfoService.register(bookInfo);

        Book book = new Book(bookInfo, BookStatus.RESERVATION);

        //when
        Long bookId = bookService.register(book);

        //then
        Assertions.assertEquals(book, bookRepository.findOne(bookId));
    }

    @Test
    public void 도서조회() throws Exception {

        //given
        Category category1 = new Category("Back-end");
        Long categoryId1 = categoryService.register(category1);

        Category category2 = new Category("Java");
        Long categoryId2 = categoryService.register(category2);

        BookInfo bookInfo1 = new BookInfo("1234", category1, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn1 = bookInfoService.register(bookInfo1);

        BookInfo bookInfo2 = new BookInfo("12345", category2, "Lee", "Spring", "Wiley", LocalDateTime.now());
        String bookInfoIsbn2 = bookInfoService.register(bookInfo2);

        Book book1 = new Book(bookInfo1, BookStatus.RESERVATION);
        Long bookId1 = bookService.register(book1);

        Book book2 = new Book(bookInfo2, BookStatus.LOAN);
        Long bookId2 = bookService.register(book2);

        //when
        List<Book> books= bookService.findBooks();

        //then
        for (Book book : books) {
            System.out.println("book.getId() = " + book.getId());
            System.out.println("book.getBookStatus() = " + book.getBookStatus());
            System.out.println();
        }
    }

    @Test
    public void 도서삭제() throws Exception {

        Category category1 = new Category("Back-end");
        Long categoryId1 = categoryService.register(category1);

        Category category2 = new Category("Java");
        Long categoryId2 = categoryService.register(category2);

        BookInfo bookInfo1 = new BookInfo("1234", category1, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn1 = bookInfoService.register(bookInfo1);

        BookInfo bookInfo2 = new BookInfo("12345", category2, "Lee", "Spring", "Wiley", LocalDateTime.now());
        String bookInfoIsbn2 = bookInfoService.register(bookInfo2);

        Book book1 = new Book(bookInfo1, BookStatus.RESERVATION);
        Long bookId1 = bookService.register(book1);

        Book book2 = new Book(bookInfo2, BookStatus.LOAN);
        Long bookId2 = bookService.register(book2);

        //when
        bookService.deleteBook(book2);

        //then
        List<Book> books= bookService.findBooks();
        for (Book book : books) {
            System.out.println("book.getId() = " + book.getId());
            System.out.println("book.getBookStatus() = " + book.getBookStatus());
            System.out.println();
        }
    }
}
