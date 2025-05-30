package seat.dao;

import seat.domain.ReservationSeatVO;

import java.util.List;

public interface ReservationSeatDao {
    void insert(ReservationSeatVO reservationSeat); //예매-좌석 매핑 정보를 저장
    List<Integer> findSeatIdsByReservationId(String reservationId); //예매 id로 좌석 id 조회
    void deleteByReservationId(String reservationId);// 예매 ID에 해당하는 매핑 정보 삭제
    List<String> findSeatCodesByReservationId(String reservationId);  // 예매 ID로 좌석 코드(seat_code) 목록 조회
}
