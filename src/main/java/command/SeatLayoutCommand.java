package command;

import command.input.InputUtil;
import command.print.ReservationViewImpl;
import seat.service.SeatService;

import java.util.Map;
import java.util.Scanner;

public class SeatLayoutCommand implements Command {

    private final SeatService seatService;
    private final int scheduleId;

    public SeatLayoutCommand(SeatService seatService, int scheduleId) {
        this.seatService = seatService;
        this.scheduleId = scheduleId;
    }

    @Override
    public void execute() {
        Map<String, Boolean> seatMap = seatService.getSeatStatusMap(scheduleId);
        ReservationViewImpl.printSeatLayout(seatMap);
        ReservationViewImpl.printLine();

        Scanner scanner = new Scanner(System.in);
        String[] seatCodes = InputUtil.nextSeatCodes("👉 예매할 좌석을 입력해주세요 (예: A1 A3): ");
        //유효성 검사 및 로직 연결해야함 -> 좌석 배치도 다음은 결제
        //유효성 검사 -> 선택한 좌석의 isReserved가 false인가?, 인원수와 좌석의 개수가 맞는가? 등등
    }
}