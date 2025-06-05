package command;

import command.input.InputUtil;
import command.print.ReservationViewImpl;
import lombok.RequiredArgsConstructor;
import reservation.service.ReservationService;
import schedule.domain.ScheduleVO;
import schedule.service.ScheduleService;
import seat.service.SeatService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
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

        //영화 id로 상영 정보 조회
        List<ScheduleVO> schedules = scheduleService.getSchedulesByMovieId(movieId);
        if(schedules.isEmpty()){
            System.out.println("해당 영화의 상영 일정이 없습니다.");
            return;
        }
        //날짜 목록 추출하기
        List<LocalDate> dates = schedules.stream()
                .map(ScheduleVO::getScreenDate)
                .distinct()
                .sorted()
                .toList();

        //날짜 선택
        ReservationViewImpl.printDateSelectionMenu(dates);
        int dateOption = InputUtil.getIntInRange("👉 입력: ", 1, dates.size());
        LocalDate selectedDate = dates.get(dateOption - 1);

        //선택한 날짜의 상영 시간 추출 -> 이거 안됨 2개 조회됨
        List<ScheduleVO> times = schedules.stream()
                .filter(s -> s.getScreenDate().equals(selectedDate))
                .sorted(Comparator.comparing(ScheduleVO::getStartTime))
                .toList();

        ReservationViewImpl.printTimeSelection(times);

        //상영 시간 선택
        int timeOption = InputUtil.getIntInRange("👉 입력: ", 1, times.size());
        ScheduleVO selectedSchedule = times.get(timeOption - 1);


        int scheduleId = selectedSchedule.getScheduleId();

        Map<String, Boolean> seatMap = seatService.getSeatStatusMap(scheduleId);
        ReservationViewImpl.printSeatLayout(seatMap);
        String[] seatCodes = InputUtil.nextSeatCodes("👉 예매할 좌석을 입력해주세요 (예: A1 A3): ");
    }
}
