package command.util;

import java.util.Scanner;

public class ConsoleUtil {

    // 노란색 라인
    public static void printLine() {
        System.out.println("\u001B[34m------------------------------------------\u001B[0m");
    }

    public static void waitForQ() {
        while (true) {
            String input = InputUtil.nextInput("[Q] 🏠 이전으로: ").trim();
            if (input.equalsIgnoreCase("Q")) return;
            System.out.println("❗ 'Q'를 입력하셔야 이전으로 돌아갑니다.");
        }
    }
}