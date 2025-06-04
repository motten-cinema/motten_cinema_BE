package command;

import command.input.InputUtil;
import command.print.ReservationViewImpl;
import lombok.RequiredArgsConstructor;
import reservation.service.ReservationService;
import schedule.service.ScheduleService;
import seat.service.SeatService;

import java.time.LocalDate;
import java.util.Map;

import static command.print.ReservationViewImpl.printMovieHeader;

//영화 예매
@RequiredArgsConstructor
public class ReserveTicketCommand implements Command {
    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final ReservationService reservationService;

    @Override
    public void execute() {
        ReservationViewImpl.printMovieHeader();
        ReservationViewImpl.printLine();
        System.out.println("🎫 예매할 영화의 ID를 입력해주세요");
        int movieId = InputUtil.nextInt("👉 입력: ");
        //영화 id로 상영정보 조회
        //LocalDate date = InputUtil.selectDate(scheduleService., "👉 입력: "); -> 영화 id로 상영 날짜 조회

        Map<String, Boolean> seatMap = seatService.getSeatStatusMap(scheduleId);
        ReservationViewImpl.printSeatLayout(seatMap);
        String[] seatCodes = InputUtil.nextSeatCodes("👉 예매할 좌석을 입력해주세요 (예: A1 A3): ");
    }
}
