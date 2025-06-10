package command;

import movie.service.MovieService;
import command.print.MovieViewImpl;

import java.util.Scanner;

import static command.util.ConsoleUtil.waitForQ;

public class ShowMovieListCommand implements Command {
    private final MovieService movieService;

    public ShowMovieListCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            MovieViewImpl.printSortMenu();
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            switch (input) {
                case "1" -> {
                    MovieViewImpl.printRatingSortMenu();
                    String sortInput = sc.nextLine().trim();

                    if (sortInput.equalsIgnoreCase("q")) {
                        return;
                    }

                    if (sortInput.equals("1")) {
                        MovieViewImpl.printSortedMovies(true);
                    } else if (sortInput.equals("2")) {
                        MovieViewImpl.printSortedMovies(false);
                    } else {
                        System.out.println("❗ 올바른 번호를 입력해주세요.");
                        return;
                    }

                    waitForQ();
                }

                case "2" -> {
                    MovieViewImpl.printGenreMenu();
                    String genreInput = sc.nextLine().trim();

                    if (genreInput.equalsIgnoreCase("q")) {
                        return;
                    }

                    String[] genres = {
                            "정치", "스릴러", "범죄", "액션",
                            "멜로", "스포츠", "애니메이션", "코미디"
                    };

                    try {
                        int genreNum = Integer.parseInt(genreInput);
                        if (genreNum >= 1 && genreNum <= genres.length) {
                            MovieViewImpl.printFilteredByGenre(genres[genreNum - 1]);
                            waitForQ();
                        } else {
                            System.out.println("❗ 번호를 1~8 사이로 입력해주세요.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❗ 숫자로 입력해주세요.");
                    }
                }

                default -> System.out.println("❗ 메뉴 번호를 다시 확인해주세요.");
            }
        }
    }

}
