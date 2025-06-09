package seat.service;

import seat.domain.ReservationSeatVO;

import java.util.List;
import java.util.Map;

public interface SeatService {

    Map<String, Boolean> getSeatStatusMap(int scheduleId); //좌석 배치도 조회
    List<ReservationSeatVO> convertSeatCodesToReservationSeats(String reservationId, List<String> seatCodes);
}
