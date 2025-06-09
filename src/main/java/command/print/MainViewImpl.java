package command.print;


import command.*;
import command.input.InputUtil;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;
import reservation.service.ReservationServiceImpl;
import schedule.service.ScheduleServiceImpl;
import seat.service.SeatServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class MainViewImpl {

    public void start() {
        MainCommand mainCommand = new MainCommand();
        mainCommand.execute();
    }

    public static void printWelcome() {
        System.out.println("""
                ███╗   ███╗ ██████╗ ██╗   ██╗██╗███████╗
                ████╗ ████║██╔═══██╗██║   ██║██║██╔════╝
                ██╔████╔██║██║   ██║██║   ██║██║█████╗
                ██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██║██╔══╝
                ██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ██║███████╗
                ╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚═╝╚══════╝
                       ░░░  M  O  V  I  E  ░░░
                      🎞️ PRESS START TO WATCH 🎬
                """);
        System.out.println("\n>> Press ENTER to start...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        System.out.println("""
                ◉ ◉ ◉  MOVIE RESERVATION SYSTEM  ◉ ◉ ◉
                ----------------------------------------
                 1. 🎟 예매하기
                        1-1. 상세보기
                        1-2. 바로 예매
                 2. 🔍 영화 조회
                        2-1. 별점 순 정렬
                        2-2. 장류 별 필터
                 3. 🧾 예매 확인/취소
                        3-1. 예매 확인 (예매 번호 조회)
                        3-2. 예매 취소 (좌석 반환 및 환불 정책)
                 4. ❌ 종료
                ----------------------------------------
                """);
    }
}