package command;

import command.util.InputUtil;
import command.print.ReservationManagementViewImpl;
import lombok.RequiredArgsConstructor;
import reservation.domain.ReservationInfoVO;
import reservation.domain.ReservationVO;
import reservation.service.ReservationService;

import java.util.List;

import static command.util.ConsoleUtil.printLine;
import static command.util.ConsoleUtil.waitForQ;

@RequiredArgsConstructor
public class ReservationCheckCommand implements Command {

    private final ReservationService reservationService;

    @Override
    public void execute() {
        while (true) {
            printLine();
            System.out.println("◉ ◉ ◉  🧾 RESERVATION MANAGEMENT  ◉ ◉ ◉");
            printLine();
            System.out.println("원하시는 메뉴 번호를 입력해주세요");
            System.out.println("1. 🔎 예매 확인");
            System.out.println("2. ❌ 예매 취소");
            System.out.println("[Q] 🏠 홈으로");
            String input = InputUtil.nextInput("\u001B[35m 👉 입력: \u001B[0m").trim();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("🏠 홈으로 돌아갑니다.");
                return;
            }

            switch (input) {
                case "1" -> checkReservation();
                case "2" -> cancelReservation();
                default -> System.out.println("❗ 올바른 메뉴 번호를 입력해주세요.");
            }
        }
    }

    private void checkReservation() {
        printLine();
        System.out.println("◉ ◉ ◉ ◉ ◉  🔍 CHECK RESERVATION  ◉ ◉ ◉ ◉ ◉");
        printLine();
        String reservationId = InputUtil.nextInput("📌 확인하실 예매 번호를 입력하세요\n👉" +
                "\u001B[35m  입력: \u001B[0m").trim();

        ReservationVO reservation =  reservationService.findReservationById(reservationId);
        if (reservation == null) {
            System.out.println("❌ 조회되지 않는 예매 번호입니다.");
            return;
        }

        ReservationInfoVO info = reservationService.getReservationById(reservationId, reservation.getScheduleId());

        if (info == null) {
            System.out.println("해당 예매 번호의 내역을 찾을 수 없습니다.");
            return;
        }

        // 좌석 ID -> 코드 변환
        List<String> seatCodes = reservationService.getSeatCodesFromIds(info.getSeats());

        ReservationManagementViewImpl.printCheckReservation(
                info.getTitle(),
                info.getScreenDate(),
                info.getStartTime().atDate(info.getScreenDate()),
                info.getTotalPerson(),
                seatCodes,
                info.getTotalPrice()
        );

        waitForQ();
    }

    private void cancelReservation() {
        printLine();
        System.out.println("◉ ◉ ◉ ◉  ❌ CANCEL RESERVATION  ◉ ◉ ◉ ◉");
        printLine();

        String reservationId = InputUtil.nextInput("📌 예매 번호를 입력하세요\n👉 " +
                "\u001B[35m 입력: \u001B[0m").trim();

        ReservationVO reservation = reservationService.findReservationById(reservationId);
        if (reservation == null) {
            System.out.println("❌ 조회되지 않는 예매 번호입니다.");
            return;
        }

        // 예매 상태 확인
        if (!"예매완료".equals(reservation.getStatus())) {
            System.out.println("⚠️ 아직 예약이 완료되지 않아 예매 취소를 진행할 수 없습니다.\n");
            return;
        }

        ReservationInfoVO info = reservationService.getReservationById(reservationId, reservation.getScheduleId());
        if (info == null) {
            System.out.println("❌ 해당 예매 번호의 상세 정보를 찾을 수 없습니다.");
            return;
        }

        String confirm = InputUtil.nextInput("\u001B[35m ❓ 정말 예매를 취소하시겠습니까? (Y/N): \u001B[0m").trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("❌ 예매 취소가 취소되었습니다.");
            return;
        }

        reservationService.deleteReservationById(reservationId);
        ReservationManagementViewImpl.printCancelReservation(reservationId, info.getTotalPrice());

        waitForQ();
    }

}