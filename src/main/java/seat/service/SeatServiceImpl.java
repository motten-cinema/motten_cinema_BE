package seat.service;

import seat.dao.SeatDao;
import seat.dao.SeatDaoImpl;
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
        List<SeatVO> seatList = seatDao.findByScheduleId(scheduleId);
        Map<String, Boolean> seatMap = new HashMap<>();
        for (SeatVO seat : seatList) {
            seatMap.put(seat.getSeatCode(), seat.getIsReserved());
        }
        return seatMap;
    }

}

