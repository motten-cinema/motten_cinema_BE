package command;

import command.input.InputUtil;
import command.print.MovieViewImpl;
import lombok.RequiredArgsConstructor;
import movie.domain.MovieVO;
import movie.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
public class ShowMovieListCommand implements Command {
    private final MovieService movieService;

    @Override
    public void execute() {
        MovieViewImpl.printMovieList();
        int movieId = InputUtil.nextInt("👉 입력: ");

        MovieViewImpl.printMovieDetail(movieId);
        List<MovieVO> movieList = movieService.getList();
        System.out.println("🎥 상영 중인 영화 목록입니다:");
        for (MovieVO movie : movieList) {
            System.out.printf("🎞️ ID: %d | 제목: %s | 장르: %s | 러닝타임: %d분\n",
                    movie.getMovieId(), movie.getTitle(), movie.getGenre(), movie.getDuration());
        }
    }
}