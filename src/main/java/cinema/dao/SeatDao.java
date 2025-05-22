package cinema.dao;

import cinema.domain.ReservationSeatVO;
import cinema.domain.SeatVO;

public interface SeatDao {
    void insert(SeatVO seatVO); //좌석 정보 저장
}
