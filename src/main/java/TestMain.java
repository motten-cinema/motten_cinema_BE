import command.Command;
import command.SeatLayoutCommand;
import command.print.MovieViewImpl;
import seat.service.SeatService;
import seat.service.SeatServiceImpl;

import java.util.Map;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MovieViewImpl.printMovieList(sc);

        int scheduleId = 1;

            SeatService seatService = new SeatServiceImpl();
            Command seatLayoutCommand = new SeatLayoutCommand(seatService, scheduleId);

            seatLayoutCommand.execute();  // 좌석 배치도 출력 + 좌석 입력
    }
}