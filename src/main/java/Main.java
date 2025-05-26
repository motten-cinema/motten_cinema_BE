import cinema.service.SeatService;
import cinema.service.SeatServiceImpl;
import console.SeatLayoutPrinter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //임시로 좌석 배치도만 넣어놨습니다!
        int scheduleId = 1;
        SeatService seatService = new SeatServiceImpl();
        Map<String, Boolean> seatMap = seatService.getSeatStatusMap(scheduleId);
        SeatLayoutPrinter.print(seatMap);
    }
}
