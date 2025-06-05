import command.Command;
import command.ReserveTicketCommand;
import command.print.MovieViewImpl;
import reservation.service.ReservationService;
import reservation.service.ReservationServiceImpl;
import schedule.service.ScheduleService;
import schedule.service.ScheduleServiceImpl;
import seat.service.SeatService;
import seat.service.SeatServiceImpl;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieViewImpl.printMovieList(sc);
        ScheduleService scheduleService = new ScheduleServiceImpl();
        SeatService seatService = new SeatServiceImpl();
        ReservationService reservationService = new ReservationServiceImpl();

        Command reserveTicketCommand = new ReserveTicketCommand(scheduleService, seatService, reservationService);
        reserveTicketCommand.execute();
    }
}