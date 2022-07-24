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
    @Transactional
    public Long reserve(Reservation reservation) {

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    /**
     * 전체 예약 조회
     */
    public List<Reservation> findAllReservs() {
        return reservationRepository.findAll();
    }

    /**
     * 전화번호로 예약 조회
     */
    public List<Reservation> findReservByPhone(Reservation reservation) {
        return reservationRepository.findByPhone(reservation.getMember());
    }

    /**
     * 예약 삭제
     */
    @Transactional
    public void deleteReserv(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Reservation findOne(Long reservationId) {
        return reservationRepository.findOne(reservationId);
    }
}
