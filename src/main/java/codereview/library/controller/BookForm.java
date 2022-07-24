package codereview.library.controller;

import codereview.library.domain.BookInfo;
import codereview.library.domain.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    private BookInfo bookInfo;
    private BookStatus bookStatus;
}
