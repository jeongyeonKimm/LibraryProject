package codereview.library.controller;

import codereview.library.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookInfoForm {

    private String isbn;
    private Category category;
    private String author;
    private String name;
    private String publisher;

}
