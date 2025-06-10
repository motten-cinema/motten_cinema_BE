package seat.service;

import seat.dao.SeatDao;
import seat.dao.SeatDaoImpl;
import seat.domain.ReservationSeatVO;
import seat.domain.SeatVO;

import java.util.HashMap;
import java.util.List;
import java.util.*;

public class SeatServiceImpl implements SeatService {
    private final SeatDao seatDao = new SeatDaoImpl();
    private final Map<Integer, Set<String>> selectedSeatsMap = new HashMap<>();

    //좌석 배치도 조회
    @Override
    public Map<String, Boolean> getSeatStatusMap(int scheduleId) {
        Map<String, Boolean> seatMap = new HashMap<>();
        try {
            List<SeatVO> seatList = seatDao.findByScheduleId(scheduleId);

            if (seatList == null || seatList.isEmpty()) {
                System.out.println("해당 스케줄의 좌석 정보가 없습니다. scheduleId: " + scheduleId);
                return seatMap;
            }

            for (SeatVO seat : seatList) {
                seatMap.put(seat.getSeatCode(), seat.getIsReserved());
            }
        } catch (Exception e) {
            System.out.println("좌석 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }

        return seatMap;
    }

    @Override
    public List<ReservationSeatVO> convertSeatCodesToReservationSeats(String reservationId, List<String> seatCodes) {
        List<ReservationSeatVO> reservationSeats = new ArrayList<>();
        for (String code : seatCodes) {
            Optional<SeatVO> seat = seatDao.findBySeatCode(code);
            if (seat.isPresent()) {
                reservationSeats.add(ReservationSeatVO.builder()
                        .reservationId(reservationId)
                        .seatId(seat.get().getSeatId())
                        .build());
            } else {
                System.out.println("❌ 좌석 코드 " + code + " 에 해당하는 좌석을 찾을 수 없습니다.");
            }
        }
        return reservationSeats;
    }
}


