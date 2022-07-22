package codereview.library.service;

import codereview.library.domain.BookInfo;
import codereview.library.repository.BookInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookInfoService {

    private final BookInfoRepository bookInfoRepository;

    /**
     * 도서 정보 등록
     */
    public void register(BookInfo bookInfo) {
        bookInfoRepository.save(bookInfo);
    }

    /**
     * 전체 도서 정보 조회
     */
    public List<BookInfo> findBookInfos() {
        return bookInfoRepository.findAll();
    }

    /**
     * isbn으로 도서 정보 조회
     */
    public BookInfo findBookInfoByIsbn(BookInfo bookInfo) {
        return bookInfoRepository.findByIsbn(bookInfo.getIsbn());
    }

    /**
     * 카테고리로 도서 정보 조회
     */
    public List<BookInfo> findBookInfoByCategory(BookInfo bookInfo) {
        return bookInfoRepository.findByCategory(bookInfo.getCategory());
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
    public void deleteBookInfo(BookInfo bookInfo) {
        bookInfoRepository.delete(bookInfo.getIsbn());
    }

}
