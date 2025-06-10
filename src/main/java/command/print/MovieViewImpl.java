package command.print;

import movie.domain.MovieVO;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static command.util.ConsoleUtil.printLine;

public class MovieViewImpl {

    private static final MovieService service = new MovieServiceImpl();

    public static void printMovieList() {
        try {
                List<MovieVO> list = service.getList();

                printLine();
                System.out.println("◉ ◉ ◉ ◉  🎟 MOVIE RESERVATION  ◉ ◉ ◉ ◉");
                printLine();
                for (MovieVO m : list) {
                    System.out.printf("ID: %-2s | %-10s | %-5s | ⭐ %.1f%n",
                            m.getMovieId(), m.getTitle(), m.getGenre(), m.getRating());
                }

        } catch (Exception e) {
            System.out.println("⚠️ 영화 목록을 불러오는 중 오류 발생: " + e.getMessage());
        }

    }


    public static void printMovieDetail(int movieId) {
        Optional<MovieVO> found = service.getById(movieId);
        if (found.isEmpty()) {
            System.out.println("해당 ID의 영화가 존재하지 않습니다.");
            return;
        }

        MovieVO m = found.get();
        printLine();
        System.out.println("📌 선택한 영화 정보");
        printLine();
        System.out.println("🎬 제목: " + m.getTitle());
        System.out.println("🎭 장르: " + m.getGenre());
        System.out.println("⭐ 평점: " + m.getRating());
        System.out.println("🎬 감독: " + m.getDirector());
        System.out.println("👥 출연진: " + m.getCast());
        System.out.println("📖 줄거리: " + m.getSummary());
        System.out.println("🕒 상영 시간: " + m.getDuration() + "분");
        System.out.println("🔞 관람 등급: " + m.getAgeLimit() + "세 이상");
        System.out.println("📅 개봉일: " + m.getReleaseDate());
        System.out.println("------------------------------------------");
    }
}
