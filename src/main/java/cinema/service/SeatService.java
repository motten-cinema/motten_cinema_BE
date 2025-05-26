package cinema.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SeatService {

    Map<String, Boolean> getSeatStatusMap(int scheduleId); //좌석 배치도 조회
}
