package movie;

import movie.domain.MovieVO;
import movie.service.MovieService;
import movie.service.MovieServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MovieTestMain {
    public static void main(String[] args) {
        MovieService service = new MovieServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n📽️ 메뉴 선택:");
            System.out.println("1. 전체 목록 보기");
            System.out.println("2. ID로 영화 조회");
            System.out.println("3. 평점순 정렬");
            System.out.println("4. 장르로 검색 ( 정치, 스릴러, 범죄, 액션 ) ");
            System.out.println("0. 종료");
            System.out.print("👉 선택: ");
            String input = sc.nextLine();

            try {
                switch (input) {
                    case "1":
                        List<MovieVO> all = service.getList();
                        System.out.println("🎬 전체 영화 목록:");
                        all.forEach(m -> System.out.println(
                                "ID: " + m.getMovie_id() + " | 제목: " + m.getTitle() + " | 평점: " + m.getRating()));
                        break;

                    case "2":
                        System.out.print("🎬 영화 ID 입력: ");
                        String id = sc.nextLine();
                        Optional<MovieVO> found = service.getById(id);
                        if (found.isPresent()) {
                            MovieVO m = found.get();
                            System.out.println("🎬 제목: " + m.getTitle());
                            System.out.println("🎭 장르: " + m.getGenre());
                            System.out.println("⭐ 평점: " + m.getRating());
                            System.out.println("🎬 감독: " + m.getDirector());
                            System.out.println("👥 출연진: " + m.getCast());
                            System.out.println("📖 줄거리: " + m.getSummary());
                            System.out.println("🕒 상영 시간: " + m.getDuration() + "분");
                            System.out.println("🔞 관람 등급: " + m.getAge_limit() + "세 이상");
                            System.out.println("📅 개봉일: " + m.getRelease_date());
                        } else {
                            System.out.println("❌ 해당 ID의 영화가 없습니다.");
                        }
                        break;

                    case "3":
                        System.out.print("📈 높은 순(1) / 📉 낮은 순(2): ");
                        String order = sc.nextLine();
                        boolean desc = "1".equals(order);
                        List<MovieVO> sorted = service.getSortedByRating(desc);
                        sorted.forEach(m -> System.out.println(
                                "ID: " + m.getMovie_id() + " | " + m.getTitle() + " | ⭐ " + m.getRating()));
                        break;

                    case "4":
                        System.out.print("🎭 장르 입력: ");
                        String genre = sc.nextLine();
                        List<MovieVO> byGenre = service.getByGenre(genre);
                        if (byGenre.isEmpty()) {
                            System.out.println("❌ 해당 장르의 영화가 없습니다.");
                        } else {
                            byGenre.forEach(m -> System.out.println(
                                    "ID: " + m.getMovie_id() + " | " + m.getTitle() + " | ⭐ " + m.getRating()));
                        }
                        break;

                    case "0":
                        System.out.println("👋 종료합니다.");
                        return;

                    default:
                        System.out.println("❌ 잘못된 입력입니다.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ 처리 중 오류 발생: " + e.getMessage());
            }
        }
    }
}
