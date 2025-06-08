package command.print;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationManagementViewImpl {

    public static void printCheckReservation(String title, LocalDate screenDate, LocalDateTime startTime, int totalPeople, List<String> seats, int totalPrice) {

        System.out.println("\uD83C\uDF9F RESERVATION INFO");
        System.out.println("\uD83C\uDFAC 영화 제목 : " + title);
        System.out.println("\uD83D\uDCC5 날짜 :" + screenDate);
        System.out.println("⏰ 상영 시간 :" + startTime);
        System.out.println("\uD83D\uDC65 인원 수 :" + totalPeople);
        System.out.print("\uD83D\uDCBA 선택 좌석 :");
        for (String seat : seats) {
            System.out.print(seat);
        }
        System.out.println();
        System.out.println("\uD83D\uDCB0 총 금액 :" + totalPrice);
        ReservationViewImpl.printLine();
    }

    public static void printCancelReservation(String reservationId, int totalPrice) {
        System.out.println("\uD83D\uDD01 다음 예매 번호에 대한 예매 취소 :" + reservationId);
        try {
            Thread.sleep(1000);
            System.out.println("⌛ 진행중.");
            Thread.sleep(1000);
            System.out.println("⌛ 진행중..");
            Thread.sleep(1000);
            System.out.println("⌛ 진행중...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✅ 예매 취소가 완료되었습니다.");
        System.out.println("\uD83D\uDCB0 결제 금액 " + totalPrice + "원에 대한 환불은 5일 이내로 완료될 예정입니다.");
    }
}
