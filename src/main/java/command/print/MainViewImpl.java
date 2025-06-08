package command.print;


import command.*;
import command.input.InputUtil;
import movie.service.*;
import reservation.service.ReservationService;
import reservation.service.ReservationServiceImpl;
import schedule.service.ScheduleService;
import schedule.service.ScheduleServiceImpl;
import seat.service.SeatService;
import seat.service.SeatServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MainViewImpl {

    private final Map<Integer, Command> commandMap = new HashMap<>();

    public MainViewImpl() {
        MovieService movieService = new MovieServiceImpl();
        ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
        SeatServiceImpl seatService = new SeatServiceImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl();

        commandMap.put(1, new ReserveTicketCommand(scheduleService, seatService, reservationService));
        commandMap.put(2, new ShowMovieListCommand(movieService));
      //  commandMap.put(3, new DummyCommand());
        commandMap.put(4, new ExitCommand());
    }

    public void start() {
        while (true) {
            printWelcome();
            printMenu();
            int choice = InputUtil.getIntInRange(">> SELECT OPTION: ", 1, 4);
            Command command = commandMap.get(choice);
            if (command != null) command.execute();
            if (choice == 4) break;
        }
    }

    private void printWelcome() {
        System.out.println("""
                ███╗   ███╗ ██████╗ ██╗   ██╗██╗███████╗
                ████╗ ████║██╔═══██╗██║   ██║██║██╔════╝
                ██╔████╔██║██║   ██║██║   ██║██║█████╗
                ██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██║██╔══╝
                ██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ██║███████╗
                ╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚═╝╚══════╝
                       ░░░  M  O  V  I  E  ░░░
                      🎞️ PRESS START TO WATCH 🎬
                """);
    }

    private void printMenu() {
        System.out.println("""
                ◉ ◉ ◉  MOVIE RESERVATION SYSTEM  ◉ ◉ ◉
                ----------------------------------------
                 1. 🎟 예매하기
                        1-1. 상세보기
                        1-2. 바로 예매
                 2. 🔍 영화 조회
                        2-1. 별점 순 정렬
                        2-2. 장류 별 필터
                 3. 🧾 예매 확인/취소
                        3-1. 예매 확인 (예매 번호 조회)
                        3-2. 예매 취소 (좌석 반환 및 환불 정책)
                 4. ❌ 종료
                ----------------------------------------
                """);
    }
}