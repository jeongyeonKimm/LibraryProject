package codereview.library.service;

import codereview.library.domain.Book;
import codereview.library.domain.BookInfo;
import codereview.library.domain.Category;
import codereview.library.repository.BookInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BookInfoServiceTest {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void 도서정보등록() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());

        //when
        String bookInfoIsbn= bookInfoService.register(bookInfo);

        //then
        assertEquals(bookInfo, bookInfoRepository.findByIsbn(bookInfoIsbn));

    }

    @Test
    public void 전체_도서정보조회() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo1 = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn= bookInfoService.register(bookInfo1);

        BookInfo bookInfo2 = new BookInfo("12345", category, "Lee", "Spring", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn2= bookInfoService.register(bookInfo2);

        //when
        List<BookInfo> findBookInfos = bookInfoService.findBookInfos();

        //then
        for (BookInfo bookInfo : findBookInfos) {
            System.out.println("BookInfo.isbn = " + bookInfo.getIsbn());
        }
    }

    @Test
    public void 도서정보조회() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo1 = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn= bookInfoService.register(bookInfo1);

        BookInfo bookInfo2 = new BookInfo("12345", category, "Lee", "Spring", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn2= bookInfoService.register(bookInfo2);

        //when
        BookInfo findBookInfo = bookInfoService.findBookInfoByIsbn(bookInfo1);
        List<BookInfo> findByCategory = bookInfoService.findBookInfoByCategory(category);
        List<BookInfo> findByName = bookInfoService.findBookInfoByName(bookInfo1);
        List<BookInfo> findByPublisher = bookInfoService.findBookInfoByPublisher(bookInfo1);
        List<BookInfo> findByAuthor = bookInfoService.findBookInfoByAuthor(bookInfo1);

        //then
        assertEquals(bookInfo1, findBookInfo);
        for (BookInfo bookInfo : findByCategory) {
            System.out.println("bookInfo.getName() = " + bookInfo.getName());
        }
        System.out.println();
        for (BookInfo bookInfo : findByName) {
            System.out.println("bookInfo.getName() = " + bookInfo.getName());
        }
        System.out.println();
        for (BookInfo bookInfo : findByPublisher) {
            System.out.println("bookInfo.getName() = " + bookInfo.getName());
        }
        System.out.println();
        for (BookInfo bookInfo : findByAuthor) {
            System.out.println("bookInfo.getName() = " + bookInfo.getName());
        }

    }

    @Test
    public void 도서정보삭제() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);

        BookInfo bookInfo1 = new BookInfo("1234", category, "Kim", "JPA", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn= bookInfoService.register(bookInfo1);

        BookInfo bookInfo2 = new BookInfo("12345", category, "Lee", "Spring", "Inflearn", LocalDateTime.now());
        String bookInfoIsbn2= bookInfoService.register(bookInfo2);

        //when
        bookInfoService.deleteBookInfo(bookInfo2);

        //then
        List<BookInfo> bookInfoList = bookInfoService.findBookInfos();
        for (BookInfo bookInfo : bookInfoList) {
            System.out.println("bookInfo.getName() = " + bookInfo.getName());
        }
    }
}
