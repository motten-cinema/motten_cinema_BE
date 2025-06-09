package command.print;


import command.*;

import static command.util.ConsoleUtil.printLine;

public class MainViewImpl {

    public void start() {
        MainCommand mainCommand = new MainCommand();
        mainCommand.execute();
    }

    public static void printWelcome() {
        System.out.println("""
                \u001B[35m
                ███╗   ███╗ ██████╗ ██╗   ██╗██╗███████╗
                ████╗ ████║██╔═══██╗██║   ██║██║██╔════╝
                ██╔████╔██║██║   ██║██║   ██║██║█████╗  
                ██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██║██╔══╝  
                ██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ██║███████╗
                ╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚═╝╚══════╝
                \u001B[0m
                      ░░░ M  O  V  I  E ░░░
                    \u001B[34m 🎞️ Press ENTER to start 🎬\u001B[0m
                """);

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        printLine();
        System.out.println("◉ ◉ ◉  MOVIE RESERVATION SYSTEM  ◉ ◉ ◉");
        printLine();

        System.out.println("""
                \u001B[34m 1. 🎟 예매하기 \u001B[0m
                    1-1. 상세보기
                    1-2. 바로 예매
                \u001B[34m 2. 🔍 영화 조회 \u001B[0m
                    2-1. 별점 순 정렬
                    2-2. 장르 별 필터
                \u001B[34m 3. 🧾 예매 확인/취소 \u001B[0m
                    3-1. 예매 확인 (예매 번호 조회)
                    3-2. 예매 취소 (좌석 반환 및 환불 정책)
                \u001B[34m 4. ✖ 종료 \u001B[0m
                ----------------------------------------
                """);
    }

}
