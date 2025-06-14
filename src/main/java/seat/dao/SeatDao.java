package seat.dao;

import seat.domain.SeatVO;

import java.util.List;
import java.util.Optional;

public interface SeatDao {
    void insert(SeatVO seatVO); //좌석 정보 저장
    void updateReservedStatus(int seatId, boolean isReserved); //좌석 상태 업데이트
    List<SeatVO> findByScheduleId(int scheduleId); //상영 시간대 좌석 조회
    SeatVO findById(int seatId); //좌석 id로 조회
    Optional<SeatVO> findBySeatCode(String seatCode); //좌석 코드로 id 조회
}
