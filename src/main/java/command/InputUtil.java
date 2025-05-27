package command;

import java.util.List;
import java.util.Scanner;
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

    // 좌석 입력
    public static String[] nextSeatCodes(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return new String[0];
        return input.split("\\s+");  // 공백 기준 분할
    }
}
