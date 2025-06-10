package command;

import lombok.RequiredArgsConstructor;
import movie.service.MovieService;

@RequiredArgsConstructor
public class ShowMovieListCommand implements Command {
    private final MovieService movieService;

    @Override
    public void execute() {
        System.out.println("""
                     \u001B[35m
                
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢀⣴⣿⣷⣄⠀⠀⣴⣿⣷⣆⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⠄⠀⠀⠀⠀⠀⠀
                ⠀⢀⣤⣦⣀⠀⠸⣿⣿⣿⡿⠀⠘⣿⣿⣿⡿⠀⠀⣠⣴⣤⠀⠀
                ⠀⣾⣿⣿⣿⣆⠀⠙⠻⠛⠁⠀⠀⠉⠻⠛⠁⢀⣾⣿⣿⣿⡇⠀
                ⠀⢹⣿⣿⣿⣿⠀⠀⠀⣴⣾⣿⣿⣷⣄⠀⠀⢸⣿⣿⣿⣿⠃⠀
                ⠀⠀⠙⢿⢿⠟⠀⠀⣼⣿⣿⣿⣿⣿⣿⣧⠀⠈⠿⡿⡟⠃⠀⠀
                ⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⣰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀
                ⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
                ⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠈⠛⠻⠻⠛⠉⠁⠁⠉⠛⠻⠻⠛⠁⠀⠀⠀⠀⠀
                ⠀⠀
                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                
                🐾 "개발" 중입니다. 메인 메뉴로 돌아갑니다. 푸하하~
                   \u001B[0m
                """);

        // 카운트 다운
        try {
            for (int i = 3; i > 0; i--) {
                System.out.print("\u001B[33m" + "⏳ " + i + "...\n" + "\u001B[0m");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}