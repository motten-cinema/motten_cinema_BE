package command.print;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ReservationViewImpl{

    public static void printSeatLayout(Map<String, Boolean> seatMap) {
        String[] rows = {"A", "B", "C"};
        int cols = 6;

        String gray = "\u001B[90m";
        String green = "\u001B[32m";
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
                    String color = reserved ? gray : green;
                    System.out.printf("|%s%s%s|   ", color, code, reset);
                } else {
                    System.out.printf("|__|   ");
                }
            }
            System.out.println();
        }
    }

    public static void printLine() {
        System.out.println("\n----------------------------------------");
    }

    public static void printPaymentInfo(String title, LocalDate screenDate, LocalDateTime startTime, int totalPeople, List<String> seats, int totalPrice) {

        System.out.println("◉ ◉ ◉ ◉ ◉  PAYMENT INFORMATION  ◉ ◉ ◉ ◉ ◉");
        printLine();
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
    }
}