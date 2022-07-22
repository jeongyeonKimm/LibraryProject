package codereview.library.service;

import codereview.library.domain.Member;
import codereview.library.domain.Reservation;
import codereview.library.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * 예약 등록
     */
    public void reserve(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /**
     * 전체 예약 조회
     */
    public void findAllReservs() {
        reservationRepository.findAll();
    }

    /**
     * 전화번호로 예약 조회
     */
    public void findReservByPhone(Reservation reservation) {
        reservationRepository.findByPhone(reservation.getMember());
    }

    /**
     * 예약 삭제
     */
    public void deleteReserv(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
