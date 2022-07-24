package codereview.library.controller;

import codereview.library.domain.Book;
import codereview.library.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationForm {

    private Long id;
    private Member member;
    private Book book;
    private LocalDateTime reservationDate;
}
