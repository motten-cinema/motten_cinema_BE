package seat.service;

import java.util.Map;

public interface SeatService {

    Map<String, Boolean> getSeatStatusMap(int scheduleId); //좌석 배치도 조회
}
