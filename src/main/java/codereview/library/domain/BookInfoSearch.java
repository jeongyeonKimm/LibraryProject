package codereview.library.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInfoSearch {

    private String isbn;
    private Category category;
    private String author;
    private String name;
    private String publisher;
}
