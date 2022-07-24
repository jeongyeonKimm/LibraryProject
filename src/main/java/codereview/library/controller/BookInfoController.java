package codereview.library.controller;

import codereview.library.domain.BookInfo;
import codereview.library.domain.BookInfoSearch;
import codereview.library.domain.Category;
import codereview.library.service.BookInfoService;
import codereview.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookInfoController {

    private final BookInfoService bookInfoService;
    private final CategoryService categoryService;

    @GetMapping("/bookinfo/new")
    public String createBookInfoForm(Model model) {

        List<Category> categoryList = categoryService.findAllCategory();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bookInfoForm", new BookInfoForm());

        return "bookinfo/createBookInfoForm";
    }

    @PostMapping("/bookinfo/new")
    public String create(@RequestParam("categoryId") Long categoryId, @Valid BookInfoForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "bookinfo/createBookInfoForm";
        }

        Category category = categoryService.findOne(categoryId);
        BookInfo bookInfo = new BookInfo(form.getIsbn(), category, form.getAuthor(), form.getName(), form.getPublisher(), LocalDateTime.now());

        bookInfoService.register(bookInfo);

        return "redirect:/";
    }

    @GetMapping("/bookinfo")
    public String list(@ModelAttribute("bookInfoSearch") BookInfoSearch bookInfoSearch, Model model) {

        List<Category> categoryList = categoryService.findAllCategory();
        List<BookInfo> bookInfoList = bookInfoService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bookInfoList", bookInfoList);

        return "/bookinfo/bookInfoList";
    }

    @GetMapping("/bookinfo/{isbn}/delete")
    public String delete(@PathVariable("isbn") String isbn) {

        BookInfo findBookInfo = bookInfoService.findBookInfoByIsbn(isbn);

        bookInfoService.deleteBookInfo(findBookInfo);

        return "redirect:/bookinfo";
    }
}
