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

                for (MovieVO m : list) {
                    System.out.printf("ID: %-2s | %-10s | %-5s | ⭐ %.1f%n",
                            m.getMovieId(), m.getTitle(), m.getGenre(), m.getRating());
                }
                printLine();
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
    public static void printSortMenu() {
        printLine();
        System.out.println("◉ ◉ ◉  🧾 VIEW MOVIE  ◉ ◉ ◉");
        printLine();
        System.out.println("원하시는 메뉴 번호를 입력해주세요");
        System.out.println("1. \uD83D\uDD0E 별점 순 정렬");
        System.out.println("2. ❌ 장르 별 필터");
        System.out.println("\n[Q]🏠 홈으로");
        System.out.print("👉 입력: ");
    }
    public static void printRatingSortMenu() {
        printLine();
        System.out.println("◉ ◉ ◉ ◉  📊 MOVIE RATING SORT   ◉ ◉ ◉ ◉");
        printLine();
        System.out.println("🎬 영화 목록을 평점 기준으로 정렬합니다.\n");
        System.out.println("1. 📈 높은 평점 순");
        System.out.println("2. 📉 낮은 평점 순");
        System.out.print("\n👉 정렬 방식을 선택해주세요: ");
    }

    public static void printSortedMovies(boolean highToLow) {
        try {
            List<MovieVO> list = service.getList();
            list.sort((m1, m2) -> {
                if (highToLow) return Double.compare(m2.getRating(), m1.getRating());
                else return Double.compare(m1.getRating(), m2.getRating());
            });

            printLine();
            if (highToLow) {
                System.out.println("◉ ◉ ◉  📈 SORTED BY HIGH RATING  ◉ ◉ ◉");
            } else {
                System.out.println("◉ ◉ ◉  📉 SORTED BY LOW RATING  ◉ ◉ ◉");
            }
            printLine();
            System.out.println("ID   | 제목                      | 평점");
            printLine();
            for (MovieVO m : list) {
                System.out.printf("%-4d | %-25s | ⭐ %.1f%n", m.getMovieId(), m.getTitle(), m.getRating());
            }
            printLine();
        } catch (Exception e) {
            System.out.println("⚠️ 영화 정렬 중 오류 발생: " + e.getMessage());
        }
    }

    public static void printGenreMenu() {
        printLine();
        System.out.println("◉ ◉ ◉ ◉ ◉  🎞 GENRE FILTER  ◉ ◉ ◉ ◉ ◉");
        printLine();
        System.out.println("📂 장르를 선택해주세요:\n");
        System.out.println("1. 정치       2. 스릴러");
        System.out.println("3. 범죄       4. 액션");
        System.out.println("5. 멜로       6. 스포츠");
        System.out.println("7. 애니메이션   8. 코미디");
        System.out.print("\n👉 입력: ");
    }

    public static void printFilteredByGenre(String genre) {
        try {
            List<MovieVO> list = service.getList();
            List<MovieVO> filtered = list.stream()
                    .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                    .toList();

            printLine();
            System.out.printf("◉ ◉ ◉ ◉ ◉  💥 %s MOVIES  ◉ ◉ ◉ ◉ ◉%n", genre.toUpperCase());
            printLine();
            System.out.println("ID   | 제목                      | 평점");
            printLine();

            if (filtered.isEmpty()) {
                System.out.println("해당 장르의 영화가 없습니다.");
            } else {
                for (MovieVO m : filtered) {
                    System.out.printf("%-4d | %-25s | ⭐ %.1f%n", m.getMovieId(), m.getTitle(), m.getRating());
                }
            }

            printLine();
        } catch (Exception e) {
            System.out.println("⚠️ 장르 필터링 중 오류 발생: " + e.getMessage());
        }
    }

}
