package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    private BookInfo bookInfo;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToMany(mappedBy = "book")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Loan> loanList = new ArrayList<>();

}
