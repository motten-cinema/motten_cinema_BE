import cinema.dao.SeatDao;
import cinema.dao.SeatDaoImpl;
import cinema.domain.SeatVO;

import java.util.List;

public class SeatDaoTestPrint {
    public static void main(String[] args) {
        SeatDao seatDao = new SeatDaoImpl();
        int scheduleId = 3;

        System.out.println("=== 좌석 삽입 ===");
        SeatVO newSeat = SeatVO.builder()
                .scheduleId(scheduleId)
                .seatCode("Z9")
                .isReserved(false)
                .build();
        seatDao.insert(newSeat);
        System.out.println("삽입 완료: " + newSeat);

        System.out.println("\n=== 스케줄 ID로 좌석 조회 ===");
        List<SeatVO> seatList = seatDao.findByScheduleId(scheduleId);
        for (SeatVO seat : seatList) {
            System.out.println(seat);
        }

        System.out.println("\n=== 예약 상태 변경 ===");
        if (!seatList.isEmpty()) {
            int targetSeatId = seatList.get(0).getSeatId();
            seatDao.updateReservedStatus(targetSeatId, true);
            System.out.println("seat_id=" + targetSeatId + " 예약 상태 true로 변경");

            System.out.println("\n=== 변경 후 다시 조회 ===");
            List<SeatVO> updatedList = seatDao.findByScheduleId(scheduleId);
            for (SeatVO seat : updatedList) {
                System.out.println(seat);
            }
        } else {
            System.out.println("해당 스케줄의 좌석이 없습니다.");
        }
    }
}
