package cinema.service;

import cinema.dao.CinemaDao;
import cinema.dao.CinemaDaoImpl;
import cinema.domain.MovieVO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CinemaServiceImpl implements CinemaService {

    private final CinemaDao dao = new CinemaDaoImpl();
    private final Scanner scanner = new Scanner(System.in); // ID, 장르 입력용

    @Override
    public void getList() {
        try {
            List<MovieVO> movies = dao.getList();
            System.out.println("🎬 전체 영화 목록");
            for (MovieVO movie : movies) {
                System.out.println("ID: " + movie.getMovie_id() + " | 제목: " + movie.getTitle() + " | 평점: ⭐ " + movie.getRating());
            }
        } catch (Exception e) {
            System.out.println("❌ 오류: " + e.getMessage());
        }
    }

    @Override
    public void getById() {
        System.out.print("🎬 영화 ID 입력: ");
        String input = scanner.nextLine();
        try {
            Optional<MovieVO> opt = dao.get(input);
            if (opt.isPresent()) {
                MovieVO m = opt.get();
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
        } catch (Exception e) {
            System.out.println("⚠️ 오류: " + e.getMessage());
        }
    }

    @Override
    public void getSortedByRating() {
        try {
            System.out.print("📈 높은 순(1) / 📉 낮은 순(2): ");
            String input = scanner.nextLine();
            boolean desc = "1".equals(input);
            List<MovieVO> list = dao.getSortedByRating(desc);
            System.out.println("🎬 정렬된 영화 목록:");
            for (MovieVO m : list) {
                System.out.println("ID: " + m.getMovie_id() + " | " + m.getTitle() + " | ⭐ " + m.getRating());
            }
        } catch (Exception e) {
            System.out.println("❌ 오류: " + e.getMessage());
        }
    }

    @Override
    public void getByGenre() {
        System.out.print("🎭 장르 입력: ");
        String genre = scanner.nextLine().trim();
        try {
            List<MovieVO> list = dao.getByGenre(genre);
            if (list.isEmpty()) {
                System.out.println("❌ 해당 장르의 영화가 없습니다.");
            } else {
                System.out.println("🎬 " + genre + " 장르 영화 목록:");
                for (MovieVO m : list) {
                    System.out.println("ID: " + m.getMovie_id() + " | " + m.getTitle() + " | ⭐ " + m.getRating());
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ 오류: " + e.getMessage());
        }
    }
}
