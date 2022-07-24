package codereview.library.controller;

import codereview.library.domain.*;
import codereview.library.service.BookInfoService;
import codereview.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookInfoService bookInfoService;

    @GetMapping("/book/new")
    public String createForm(Model model) {

        List<BookInfo> bookInfoList = bookInfoService.findAll();

        model.addAttribute("bookInfoList", bookInfoList);
        model.addAttribute("bookForm", new BookForm());
        return "books/createBookForm";
    }

    @PostMapping("/book/new")
    public String create(@RequestParam("isbn") String isbn, @Valid BookForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "books/createBookForm";
        }

        BookInfo bookInfo = bookInfoService.findBookInfoByIsbn(isbn);
        Book book = new Book(bookInfo, BookStatus.NONE);
        bookService.register(book);

        return "redirect:/";
    }

    @GetMapping("/book")
    public String list(@ModelAttribute("bookSearch") BookSearch bookSearch, Model model) {

        List<BookInfo> bookInfoList = bookInfoService.findAll();
        List<Book> books = bookService.findBooks();

        model.addAttribute("bookInfoList", bookInfoList);
        model.addAttribute("books", books);
        return "books/bookList";
    }


    @GetMapping("/book/{bookId}/delete")
    public String delete(@PathVariable("bookId") Long bookId) {

        Book findBook = bookService.findOne(bookId);

        bookService.deleteBook(findBook);
        bookInfoService.deleteBookInfo(findBook.getBookInfo());

        return "redirect:/book";
    }
}
