import cinema.dao.ReservationSeatDao;
import cinema.dao.ReservationSeatDaoImpl;
import cinema.domain.ReservationSeatVO;

import java.util.List;

public class ReservationSeatDaoTestMain {
    public static void main(String[] args) {
        ReservationSeatDao dao = new ReservationSeatDaoImpl();

        // 삽입 테스트
        dao.insert(ReservationSeatVO.builder()
                .reservationId("FD289")
                .seatId(1)
                .build());

        // 조회 테스트
        List<ReservationSeatVO> seats = dao.findByReservationId("FD289");
        System.out.println("== 조회 결과 ==");
        seats.forEach(System.out::println);

        // 삭제 테스트
        dao.deleteByReservationId("FD289");
        System.out.println("== 삭제 완료 ==");
    }
}