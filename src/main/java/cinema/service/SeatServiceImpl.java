package cinema.service;

import cinema.dao.SeatDao;
import cinema.dao.SeatDaoImpl;
import cinema.domain.SeatVO;

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

     //--------------------------------------------------------------------------------
    // 좌석 선택
    @Override
    public void selectSeats(int scheduleId, List<String> seatCodes) {
        selectedSeatsMap.putIfAbsent(scheduleId, new HashSet<>());
        selectedSeatsMap.get(scheduleId).addAll(seatCodes);
    }

    // 선택한 좌석 조회
    @Override
    public Set<String> getSelectedSeats(int scheduleId) {
        return selectedSeatsMap.getOrDefault(scheduleId, Collections.emptySet());
    }
}

