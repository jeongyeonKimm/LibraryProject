package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "book")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Loan> loanList = new ArrayList<>();

}
