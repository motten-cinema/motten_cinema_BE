package command.print;

import movie.domain.MovieVO;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MovieViewImpl {

    private static final MovieService service = new MovieServiceImpl();

    public static void printMovieList(Scanner sc) {
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
                System.out.print("👉 메뉴 번호를 입력해주세요: ");

                String input = sc.nextLine().trim();

                switch (input) {
                    case "1":
                        printMovieDetail(sc);  // 상세보기 보고 돌아오면 다시 루프 반복됨
                        break;
                    case "2":
                        System.out.println("🎫 예매 번호 입력 기능은 준비 중입니다.");
                        break;
                    case "Q":
                    case "q":
                        System.out.println("🏠 홈으로 돌아갑니다.");
                        return; // 루프 종료 = 함수 종료
                    default:
                        System.out.println("❌ 잘못된 입력입니다.");
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ 영화 목록을 불러오는 중 오류 발생: " + e.getMessage());
        }
    }


    private static final String OPTION_RETRY = "R";
    private static final String OPTION_QUIT = "Q";

    public static void printMovieDetail(Scanner sc) {
        boolean running = true;

        while (running) {
            System.out.println("\n◉ ◉ ◉ ◉ ◉  🎟 MOVIE Detail  ◉ ◉ ◉ ◉ ◉");
            System.out.println("----------------------------------------");
            System.out.print("🎬 상세보기를 원하시는 영화의 ID를 입력해주세요\n👉 입력: ");
            String inputId = sc.nextLine().trim();

            try {
                int movieId = Integer.parseInt(inputId);
                Optional<MovieVO> found = service.getById(movieId);

                if (found.isPresent()) {
                    MovieVO m = found.get();
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

                    System.out.println("[Q]🏠 홈으로");
                    sc.nextLine(); // 입력 대기용
                    running = false; // 반복 종료
                } else {
                    ReservationViewImpl.printInvalidMovieId();
                    String retry = sc.nextLine().trim();
                    if (!OPTION_RETRY.equalsIgnoreCase(retry)) {
                        System.out.println("🏠 홈으로 돌아갑니다.");
                        running = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자 형식의 영화 ID를 입력해주세요.");
            } catch (Exception e) {
                System.out.println("⚠️ 오류 발생: " + e.getMessage());
                running = false;
            }
        }
    }
}