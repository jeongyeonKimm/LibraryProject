package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private BookInfo bookInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;

}
