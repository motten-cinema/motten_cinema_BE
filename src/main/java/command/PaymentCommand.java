package command;

import command.print.ReservationViewImpl;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import command.util.InputUtil;

@RequiredArgsConstructor
public class PaymentCommand implements Command {
    private final String reservationId;
    private final String movieTitle;
    private final LocalDate screenDate;
    private final LocalTime startTime;
    private final int personCount;
    private final List<String> seats;
    private final int totalPrice;

    @Override
    public void execute() {
        ReservationViewImpl.printPaymentInfo(movieTitle, screenDate, startTime, personCount, seats, totalPrice);

        String confirm = InputUtil.nextInput("👉 결제를 진행하시겠습니까? (y/n): ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("결제가 취소되었습니다.");
            return;
        }

        String receipt = InputUtil.nextInput("🧾 현금영수증을 발급하시겠습니까? (y/n): ");
        if (receipt.equalsIgnoreCase("y")) {
            String phone = InputUtil.nextInput("📱 전화번호를 입력해주세요: ");
            System.out.println("✅ 현금영수증 발급 완료 (전화번호: " + phone + ")");
        }

        try {
            ReservationViewImpl.printPaymentProcess(reservationId, LocalDateTime.now());
        } catch (SQLException e) {
            System.out.println("⚠️ 결제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}