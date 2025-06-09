package command;

import command.input.InputUtil;
import command.print.MovieViewImpl;
import command.print.ReservationViewImpl;
import lombok.RequiredArgsConstructor;
import movie.domain.MovieVO;
import movie.service.MovieService;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowMovieListCommand implements Command {
    private final MovieService movieService;

    @Override
    public void execute() {

    }
}