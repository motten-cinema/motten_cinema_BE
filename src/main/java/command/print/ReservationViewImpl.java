package command.print;


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
}