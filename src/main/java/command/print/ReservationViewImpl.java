package command.print;



import schedule.domain.ScheduleVO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static command.util.ConsoleUtil.printLine;

public class ReservationViewImpl{

    public static void printMovieHeader(){
        printLine();
        System.out.println("◉ ◉ ◉ ◉ ◉  🎟 MOVIE Reservation  ◉ ◉ ◉ ◉ ◉");
    }

    //예매할 수 있는 날짜를 받아서  출력
    public static void printDateSelectionMenu(List<LocalDate> dates){
        System.out.println("------------------------------------------");
        System.out.println("📅 예매하실 날짜를 선택해주세요\n");

        for(int i = 0; i < dates.size(); i++){
            System.out.printf("%d. %s%n", i + 1, dates.get(i));
        }

    }
    //상영 시간표를 받아서 출력
    public static void printTimeSelection(List<ScheduleVO> schedules){
        printLine();
        System.out.println("\n◉ ◉ ◉  ⏰ MOVIE TIME SELECTION  ◉ ◉ ◉");
        printLine();
        System.out.println("📅 상영 시간표 (선택 날짜 기준)\n");

        for(int i = 0; i < schedules.size(); i++){
            ScheduleVO s = schedules.get(i);
            System.out.printf("%d. ⏰ %s - %s%n", i + 1, s.getStartTime(), s.getEndTime());
        }
    }
    public static void printSeatLayout(Map<String, Boolean> seatMap) {
        String[] rows = {"A", "B", "C"};
        int cols = 6;

        String gray = "\u001B[90m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";

        System.out.println("\n💺 좌석 배치도");
        System.out.println(" ========================================");
        System.out.println("║   ___   ___  _ __   ___   ___  _ __    ║");
        System.out.println("║  / __| / __|| '__| / _ \\ / _ \\| '_ \\   ║");
        System.out.println("║  \\__ \\| (__ | |   |  __/|  __/| | | |  ║");
        System.out.println("║  |___/ \\___||_|    \\___| \\___||_| |_|  ║");
        System.out.println("║                                        ║");
        System.out.println(" ========================================\n");

        for (String row : rows) {
            for (int col = 1; col <= cols; col++) {
                String code = row + col;
                if (seatMap.containsKey(code)) {
                    boolean reserved = seatMap.get(code);
                    String color = reserved ? gray : blue;
                    System.out.printf("|%s%s%s|   ", color, code, reset);
                } else {
                    System.out.printf("|__|   ");
                }
            }
            System.out.println();
        }
    }
    public static void printInvalidMovieId() {
        printLine();
        System.out.println("❌ 입력하신 ID의 영화를 찾지 못했습니다. 다시 입력해주세요.");
        printLine();
        System.out.println("[R] 🔁 다시 입력하기");
        System.out.println("[Q] 🏠 홈으로");
        System.out.print("👉 입력: ");
    }

    public static void printPaymentInfo(String title, LocalDate screenDate, LocalTime startTime, int totalPeople, List<String> seats, int totalPrice) {
        printLine();
        System.out.println("◉ ◉ ◉ ◉ ◉  PAYMENT INFORMATION  ◉ ◉ ◉ ◉ ◉");
        printLine();
        System.out.println("\uD83C\uDFAC 영화 제목 : " + title);
        System.out.println("\uD83D\uDCC5 날짜 :" + screenDate);
        System.out.println("⏰ 상영 시간 :" + startTime);
        System.out.println("\uD83D\uDC65 인원 수 :" + totalPeople);
        System.out.println("\uD83D\uDCBA 선택 좌석 : " + String.join(", ", seats));
        System.out.println();
        System.out.println("\uD83D\uDCB0 총 금액 :" + totalPrice);
    }
    public static void printCashReceiptOption(){
        System.out.print("결제하실 예매 번호를 입력해주세요. 예(AB123) : ");
        System.out.println("");
        printLine();
        System.out.println("◉ ◉ ◉ ◉ ◉ CASH RECEIPT  ◉ ◉ ◉ ◉ ◉");
        printLine();
        System.out.print("\uD83D\uDCC4 현금영수증을 발급하시겠습니까? (y/n) : ");
        System.out.println("------------------------------------------");
        System.out.println("");
        System.out.print("📱 전화번호를 입력해주세요. 예(010-1234-5678) : ");
    }
    public static void printPaymentProcess(String reservationId, LocalDateTime paymentTime) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");

        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("💳 결제 진행 중...");
        System.out.print("");
        try {
            Thread.sleep(1000);
            System.out.println("▓          10%");
            Thread.sleep(1000);
            System.out.println("▓▓▓▓▓      50%");
            Thread.sleep(1000);
            System.out.println("▓▓▓▓▓▓▓▓▓▓ 100%");
            Thread.sleep(500);
            System.out.println("");
            System.out.println("");
            System.out.println("✅ 결제가 완료되었습니다. 감사합니다!");
            System.out.println("예몌 번호 : " + reservationId);
            System.out.println("결제 시각 : " + paymentTime.format(formatter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}