package command.print;



import schedule.domain.ScheduleVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

public class ReservationViewImpl{

    public static void printMovieHeader(){
        System.out.println("◉ ◉ ◉ ◉ ◉  🎟 MOVIE Reservation  ◉ ◉ ◉ ◉ ◉");
    }

    //예매할 수 있는 날짜를 받아서  출력
    public static void printDateSelectionMenu(List<LocalDate> dates){
        System.out.println("----------------------------------------");
        System.out.println("📅 예매하실 날짜를 선택해주세요\n");

        for(int i = 0; i < dates.size(); i++){
            System.out.printf("%d. %s%n", i + 1, dates.get(i));
        }

    }
    //상영 시간표를 받아서 출력
    public static void printTimeSelection(List<ScheduleVO> schedules){
        System.out.println("\n◉ ◉ ◉  ⏰ MOVIE TIME SELECTION  ◉ ◉ ◉");
        System.out.println("----------------------------------------");
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