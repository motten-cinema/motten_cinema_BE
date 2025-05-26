package cinema.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SeatService {
    //좌석 선택, 선택한 좌석 조회는 다른 service에서(아마도 예매?) 갖다 쓰면 될 것 같습니다!
    void selectSeats(int scheduleId, List<String> seatCodes); //좌석 선택
    Set<String> getSelectedSeats(int scheduleId);  // 선택한 좌석 조회
    Map<String, Boolean> getSeatStatusMap(int scheduleId); //좌석 배치도 조회
}
