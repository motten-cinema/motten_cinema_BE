package command;

import command.util.InputUtil;
import command.print.MainViewImpl;
import movie.service.*;
import reservation.service.ReservationServiceImpl;
import schedule.service.ScheduleServiceImpl;
import seat.service.SeatServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MainCommand implements Command {
    private final Map<Integer, Command> commandMap = new HashMap<>();

    public MainCommand() {
        // 의존성 주입
        MovieService movieService = new MovieServiceImpl();
        ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
        SeatServiceImpl seatService = new SeatServiceImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl();

        commandMap.put(1, new ReserveTicketCommand(scheduleService, seatService, reservationService));
        commandMap.put(2, new ShowMovieListCommand(movieService));
        commandMap.put(3, new ReservationCheckCommand(reservationService));
        commandMap.put(4, new ExitCommand());
    }

    @Override
    public void execute() {
        boolean first = true;

        while (true) {
            if (first) {
                MainViewImpl.printWelcome();
                first = false;
            }

            MainViewImpl.printMenu();
            String input = InputUtil.nextInput("\u001B[35m >> SELECT OPTION: ");

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("🏠 메인 메뉴로 돌아갑니다.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                Command command = commandMap.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("올바른 메뉴 번호를 입력해주세요.");
                }

                if (choice == 4) break;

            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하거나 Q를 입력하세요.");
            }
        }
    }

}
