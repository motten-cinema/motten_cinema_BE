package cinema;

import cinema.service.CinemaService;
import cinema.service.CinemaServiceImpl;

import java.util.Scanner;

public class CinemaTestMain {
    public static void main(String[] args) {
        CinemaService service = new CinemaServiceImpl();
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

            switch (input) {
                case "1":
                    service.getList();
                    break;
                case "2":
                    service.getById();
                    break;
                case "3":
                    service.getSortedByRating();
                    break;
                case "4":
                    service.getByGenre();
                    break;
                case "0":
                    System.out.println("👋 종료합니다.");
                    return;
                default:
                    System.out.println("❌ 잘못된 입력입니다.");
            }
        }
    }
}
