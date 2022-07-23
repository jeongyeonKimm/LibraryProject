package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class BookInfo {

    @Id
    @Column(nullable = false)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    protected BookInfo() {
    }

    public BookInfo(String isbn, Category category, String author, String name, String publisher, LocalDateTime createdDate) {
        this.isbn = isbn;
        this.category = category;
        this.author = author;
        this.name = name;
        this.publisher = publisher;
        this.createdDate = createdDate;
    }
}
