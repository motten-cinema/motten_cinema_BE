package command;

import lombok.RequiredArgsConstructor;
import movie.service.MovieService;

@RequiredArgsConstructor
public class ShowMovieListCommand implements Command {
    private final MovieService movieService;

    @Override
    public void execute() {

    }
}