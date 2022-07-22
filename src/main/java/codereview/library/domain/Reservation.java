package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    protected Reservation(){
    }

    public Reservation(Member member, Book book, LocalDateTime reservationDate) {
        this.member = member;
        this.book = book;
        this.reservationDate = reservationDate;
    }
}
