package cinema.dao;

import cinema.domain.ReservationSeatVO;

import java.util.List;

public interface ReservationSeatDao {
    void insert(ReservationSeatVO reservationSeat); //예매-좌석 매핑 정보를 저장
    List<ReservationSeatVO> findByReservationId(String reservationId); //예매 id로 조회
    void deleteByReservationId(String reservationId);// 예매-좌석 정보 삭제
}
