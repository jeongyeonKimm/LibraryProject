package codereview.library.service;

import codereview.library.controller.BookInfoForm;
import codereview.library.domain.BookInfo;
import codereview.library.domain.Category;
import codereview.library.domain.Member;
import codereview.library.repository.BookInfoRepository;
import codereview.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookInfoService {

    private final BookInfoRepository bookInfoRepository;

    /**
     * 도서 정보 등록
     */
    @Transactional
    public String register(BookInfo bookInfo) {

        bookInfoRepository.save(bookInfo);
        return bookInfo.getIsbn();
    }

    public List<BookInfo> findAll() {
        return bookInfoRepository.findAll();
    }

    public List<BookInfo> findBookInfo(BookInfoForm bookInfoForm) {
        return bookInfoRepository.findByString(bookInfoForm);
    }

    /**
     * isbn으로 도서 정보 조회
     */
    public BookInfo findBookInfoByIsbn(String isbn) {
        return bookInfoRepository.findByIsbn(isbn);
    }

    /**
     * 카테고리로 도서 정보 조회
     */
    public List<BookInfo> findBookInfoByCategory(Category category) {
        return bookInfoRepository.findByCategory(category);
    }

    /**
     * 도서명으로 도서 정보 조회
     */
    public List<BookInfo> findBookInfoByName(BookInfo bookInfo) {
        return bookInfoRepository.findByName(bookInfo.getName());
    }

    /**
     * 출판사로 도서 정보 조회
     */
    public List<BookInfo> findBookInfoByPublisher(BookInfo bookInfo) {
        return bookInfoRepository.findByPublisher(bookInfo.getPublisher());
    }

    /**
     * 저자로 도서 정보 조회
     */
    public List<BookInfo> findBookInfoByAuthor(BookInfo bookInfo) {
        return bookInfoRepository.findByAuthor(bookInfo.getAuthor());
    }

    /**
     * 도서 정보 삭제
     */
    @Transactional
    public void deleteBookInfo(BookInfo bookInfo) {
        bookInfoRepository.delete(bookInfo);
    }
}