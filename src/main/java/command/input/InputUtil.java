package command.input;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    // 정수 입력 (범위 제한)
    public static int getIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine());
                if (value >= min && value <= max) return value;
                else System.out.println("범위를 벗어났습니다. 다시 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    //정수 입력 (제한x)
    public static int nextInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    public static LocalDate selectDate(List<LocalDate> dates, String prompt) {
        for (int i = 0; i < dates.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, dates.get(i));
        }

        while (true) {
            System.out.print(prompt);
            try {
                int option = Integer.parseInt(sc.nextLine());
                if (option >= 1 && option <= dates.size()) {
                    return dates.get(option - 1);
                } else {
                    System.out.println("범위를 벗어났습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
    // 좌석 입력
    public static String[] nextSeatCodes(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return new String[0];
        return input.split("\\s+");  // 공백 기준 분할
    }
}
