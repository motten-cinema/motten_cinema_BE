package command.print;

import movie.domain.MovieVO;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MovieViewImpl {

    private static final MovieService service = new MovieServiceImpl();

    public static void printMovieList() {
        try {
            while (true) {
                List<MovieVO> list = service.getList();

                System.out.println("\n◉ ◉ ◉ ◉  🎟 MOVIE RESERVATION  ◉ ◉ ◉ ◉");
                System.out.println("----------------------------------------");
                for (MovieVO m : list) {
                    System.out.printf("ID: %-2s | %-10s | %-5s | ⭐ %.1f%n",
                            m.getMovieId(), m.getTitle(), m.getGenre(), m.getRating());
                }
                System.out.println("----------------------------------------");
                System.out.println("1. 📖 상세보기");
                System.out.println("2. 🎫 예매 번호로 예매하기");
                System.out.println("[Q] 🏠 홈으로");
                //  System.out.print("👉 메뉴 번호를 입력해주세요: ");

            }
        } catch (Exception e) {
            System.out.println("⚠️ 영화 목록을 불러오는 중 오류 발생: " + e.getMessage());
        }

    }


    public static void printMovieDetail(int movieId) {
        boolean running = true;

        while (running) {
//            System.out.println("\n◉ ◉ ◉ ◉ ◉  🎟 MOVIE Detail  ◉ ◉ ◉ ◉ ◉");
//            System.out.println("----------------------------------------");
//            System.out.print("🎬 상세보기를 원하시는 영화의 ID를 입력해주세요\n👉 입력: ");
//            String inputId = sc.nextLine().trim();

            Optional<MovieVO> found = service.getById(movieId);

            MovieVO m = found.get();
            System.out.println("----------------------------------------");
            System.out.println("📌 선택한 영화 정보");
            System.out.println("----------------------------------------");
            System.out.println("🎬 제목: " + m.getTitle());
            System.out.println("🎭 장르: " + m.getGenre());
            System.out.println("⭐ 평점: " + m.getRating());
            System.out.println("🎬 감독: " + m.getDirector());
            System.out.println("👥 출연진: " + m.getCast());
            System.out.println("📖 줄거리: " + m.getSummary());
            System.out.println("🕒 상영 시간: " + m.getDuration() + "분");
            System.out.println("🔞 관람 등급: " + m.getAgeLimit() + "세 이상");
            System.out.println("📅 개봉일: " + m.getReleaseDate());
            System.out.println("----------------------------------------");

        }
    }
}